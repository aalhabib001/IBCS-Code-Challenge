package bd.info.habib.ibcscodechallenge.repository;

import bd.info.habib.ibcscodechallenge.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {
}
