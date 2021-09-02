package bd.info.habib.ibcscodechallenge.repository;

import bd.info.habib.ibcscodechallenge.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
}
