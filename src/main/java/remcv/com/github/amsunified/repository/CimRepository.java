package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.entity.Cim;

public interface CimRepository extends JpaRepository<Cim, String>  {

    Cim findByCim(String cim);

}
