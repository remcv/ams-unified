package remcv.com.github.amsunified.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import remcv.com.github.amsunified.model.dto.PharmacyOrderDto;
import remcv.com.github.amsunified.model.entity.PharmacyOrder;
import remcv.com.github.amsunified.repository.CimRepository;
import remcv.com.github.amsunified.repository.DepartmentAliasRepository;
import remcv.com.github.amsunified.repository.PharmacyOrderRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyOrderService {

    // fields
    private final PharmacyOrderRepository pharmacyOrderRepository;
    private final PharmacyOrdersCsvImporter pharmacyOrdersCsvImporter;
    private final CimRepository cimRepository;
    private final DepartmentAliasRepository departmentAliasRepository;

    // constructor
    @Autowired
    public PharmacyOrderService(
            PharmacyOrderRepository pharmacyOrderRepository, PharmacyOrdersCsvImporter pharmacyOrdersCsvImporter,
            CimRepository cimRepository, DepartmentAliasRepository departmentAliasRepository) {
        this.pharmacyOrderRepository = pharmacyOrderRepository;
        this.pharmacyOrdersCsvImporter = pharmacyOrdersCsvImporter;
        this.cimRepository = cimRepository;
        this.departmentAliasRepository = departmentAliasRepository;
    }

    // methods
    public List<PharmacyOrder> findOrders(int page, int size, String sortField, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortField));
        return pharmacyOrderRepository.findAll(pageable).stream().toList();
    }

    @Transactional
    public List<String> addOrdersFromCsv(String filePath, String delim, String dateFormat) {
        List<PharmacyOrderDto> importedOrdersDto = pharmacyOrdersCsvImporter.importOrders(filePath, delim, dateFormat);

        // check for new CIM codes
        List<String> newCimCodes = checkNewCim(importedOrdersDto);

        if (newCimCodes.size() > 0) {
            return newCimCodes;
        }

        // check for time/day overlap
        if (checkTimeOverlap(importedOrdersDto)) {
            throw new RuntimeException("The imported pharmacy orders overlap in time with the existing database orders.");
        }

        // transform dto to entity
        Collection<PharmacyOrder> importedOrders = importedOrdersDto
                .stream()
                .map(this::mapPharmacyOrderDtoToEntity)
                .collect(Collectors.toSet());

        pharmacyOrderRepository.saveAll(importedOrders);

        return null;
    }

    private PharmacyOrder mapPharmacyOrderDtoToEntity(PharmacyOrderDto orderDto) {
        PharmacyOrder pharmacyOrder = new PharmacyOrder();
        pharmacyOrder.setHospitalizationType(orderDto.getHospitalizationType());
        pharmacyOrder.setOrderNumber(orderDto.getOrderNumber());
        pharmacyOrder.setFo(orderDto.getFo());
        pharmacyOrder.setCnp(orderDto.getCnp());
        pharmacyOrder.setOrderDate(orderDto.getOrderDate());
        pharmacyOrder.setDepartmentAlias(departmentAliasRepository.findByAlias(orderDto.getDepartmentAlias()));
        pharmacyOrder.setCim(cimRepository.getReferenceById(orderDto.getCim()));
        pharmacyOrder.setPhysicianId(orderDto.getPhysicianId());
        pharmacyOrder.setQuantity(orderDto.getQuantity());

        return pharmacyOrder;
    }

    public List<String> checkNewCim(List<PharmacyOrderDto> inputOrders) {
        List<String> inputCim = inputOrders
                .stream()
                .map(PharmacyOrderDto::getCim)
                .distinct()
                .toList();

        List<String> dbCim = getDbCim();

        return inputCim
                .stream()
                .filter(cim -> !dbCim.contains(cim))
                .toList();
    }

    private boolean checkTimeOverlap(List<PharmacyOrderDto> inputOrders) {
        LocalDate minInputDate = inputOrders
                .stream()
                .map(PharmacyOrderDto::getOrderDate)
                .min(Comparator.naturalOrder())
                .get();

        LocalDate maxDbDate = pharmacyOrderRepository.findMaxOrderDate();

        return minInputDate.isBefore(maxDbDate);
    }

    private List<String> getDbCim() {
        return pharmacyOrderRepository.findDistinctCimCodes();
    }

}
