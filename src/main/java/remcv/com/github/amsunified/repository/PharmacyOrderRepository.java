package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import remcv.com.github.amsunified.model.entity.PharmacyOrder;

import java.time.LocalDate;
import java.util.List;

public interface PharmacyOrderRepository extends JpaRepository<PharmacyOrder, Long> {

    @Query(nativeQuery = true, value = "SELECT MAX(order_date) FROM pharmacy_orders")
    LocalDate findMaxOrderDate();

    @Query(nativeQuery = true, value = "SELECT DISTINCT cim FROM pharmacy_orders")
    List<String> findDistinctCimCodes();

}
