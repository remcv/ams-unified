package remcv.com.github.amsunified.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import remcv.com.github.amsunified.model.PharmacyOrder;
import remcv.com.github.amsunified.repository.PharmacyOrderRepository;

import java.util.List;

@Service
public class PharmacyOrderService {

    // fields
    private final PharmacyOrderRepository pharmacyOrderRepository;

    // constructor
    @Autowired
    public PharmacyOrderService(PharmacyOrderRepository pharmacyOrderRepository) {
        this.pharmacyOrderRepository = pharmacyOrderRepository;
    }

    // methods
    public List<PharmacyOrder> findOrders(int page, int size, String sortField, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortField));
        return pharmacyOrderRepository.findAll(pageable).stream().toList();
    }

}
