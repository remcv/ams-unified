package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.PharmacyOrder;

public interface PharmacyOrderRepository extends JpaRepository<PharmacyOrder, Long> {
}
