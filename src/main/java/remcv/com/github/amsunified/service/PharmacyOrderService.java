package remcv.com.github.amsunified.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import remcv.com.github.amsunified.model.dto.PharmacyOrderDto;
import remcv.com.github.amsunified.model.entity.DepartmentAlias;
import remcv.com.github.amsunified.model.entity.PharmacyOrder;
import remcv.com.github.amsunified.repository.CimRepository;
import remcv.com.github.amsunified.repository.DepartmentAliasRepository;
import remcv.com.github.amsunified.repository.PharmacyOrderRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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

    public Set<String> addOrdersFromCsv(String filePath, String delim, String dateFormat) {
        // setup
        Map<String, DepartmentAlias> departmentAliasMap = departmentAliasRepository.findAll()
                .stream()
                .collect(Collectors.toMap(DepartmentAlias::getAlias, Function.identity()));

        // import orders CSV -> POJOs
        List<PharmacyOrderDto> importedOrdersDto = pharmacyOrdersCsvImporter.importOrders(filePath, delim, dateFormat);

        // validation: 1. new CIM codes, 2. time overlap
        Set<String> newCimCodes = checkNewCim(importedOrdersDto);

        if (newCimCodes.size() > 0) {
            return newCimCodes;
        }

        // check for time/day overlap
        boolean timeIntervalOverlaps = isTimeOverlap(importedOrdersDto);

        if (timeIntervalOverlaps) {
            throw new RuntimeException("The imported pharmacy orders overlap in time with the existing database orders.");
        }

        // transform dto to entity
        List<PharmacyOrder> importedOrders = importedOrdersDto
                .stream()
                .map(dto -> mapPharmacyOrderDtoToEntity(dto, departmentAliasMap))
                .toList();

        pharmacyOrderRepository.saveAll(importedOrders);

        return null;
    }

    private PharmacyOrder mapPharmacyOrderDtoToEntity(PharmacyOrderDto orderDto, Map<String, DepartmentAlias> departmentAliasMap) {
        // check department alias
        String inputAlias = orderDto.getDepartmentAlias();

        if (Objects.isNull(departmentAliasMap.get(inputAlias))) {
            throw new RuntimeException("Department alias is unknown --> " + orderDto.getDepartmentAlias());
        }

        PharmacyOrder pharmacyOrder = new PharmacyOrder();
        pharmacyOrder.setHospitalizationType(orderDto.getHospitalizationType());
        pharmacyOrder.setOrderNumber(orderDto.getOrderNumber());
        pharmacyOrder.setFo(orderDto.getFo());
        pharmacyOrder.setCnp(orderDto.getCnp());
        pharmacyOrder.setOrderDate(orderDto.getOrderDate());
        pharmacyOrder.setDepartmentAlias(departmentAliasMap.get(orderDto.getDepartmentAlias()));
        pharmacyOrder.setCim(cimRepository.getReferenceById(orderDto.getCim()));
        pharmacyOrder.setPhysicianId(orderDto.getPhysicianId());
        pharmacyOrder.setQuantity(orderDto.getQuantity());

        return pharmacyOrder;
    }

    public Set<String> checkNewCim(List<PharmacyOrderDto> inputOrders) {
        Set<String> inputCim = inputOrders
                .stream()
                .map(PharmacyOrderDto::getCim)
                .collect(Collectors.toSet());

        List<String> dbCim = getDbCim();

        return inputCim
                .stream()
                .filter(cim -> !dbCim.contains(cim))
                .collect(Collectors.toSet());
    }

    private boolean isTimeOverlap(List<PharmacyOrderDto> inputOrders) {
        LocalDate minInputDate = inputOrders
                .stream()
                .map(PharmacyOrderDto::getOrderDate)
                .min(Comparator.naturalOrder())
                .get();

        LocalDate maxDbDate = pharmacyOrderRepository.findMaxOrderDate();

        return minInputDate.isEqual(maxDbDate) || minInputDate.isBefore(maxDbDate);
    }

    private List<String> getDbCim() {
        return cimRepository.findDistinctCimCodes();
    }

}
