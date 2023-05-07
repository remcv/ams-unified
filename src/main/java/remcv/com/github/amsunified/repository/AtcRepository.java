package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.Atc;
import remcv.com.github.amsunified.model.AtcId;

public interface AtcRepository extends JpaRepository<Atc, AtcId> {
}
