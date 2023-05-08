package remcv.com.github.amsunified.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import remcv.com.github.amsunified.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
