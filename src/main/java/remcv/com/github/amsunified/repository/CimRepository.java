package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import remcv.com.github.amsunified.model.entity.Cim;

import java.util.List;

public interface CimRepository extends JpaRepository<Cim, String>  {

    Cim findByCim(String cim);

    @Query(nativeQuery = true, value = "SELECT DISTINCT cim FROM cim")
    List<String> findDistinctCimCodes();

}
