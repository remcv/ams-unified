package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.DepartmentAlias;
import remcv.com.github.amsunified.model.DepartmentAliasId;

public interface DepartmentAliasRepository extends JpaRepository<DepartmentAlias, DepartmentAliasId> {
}
