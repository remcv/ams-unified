package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.entity.DepartmentAlias;
import remcv.com.github.amsunified.model.entity.DepartmentAliasId;

public interface DepartmentAliasRepository extends JpaRepository<DepartmentAlias, DepartmentAliasId> {

    DepartmentAlias findByAlias(String alias);

}
