package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.entity.Atc;
import remcv.com.github.amsunified.model.entity.AtcId;

public interface AtcRepository extends JpaRepository<Atc, AtcId> {
}
