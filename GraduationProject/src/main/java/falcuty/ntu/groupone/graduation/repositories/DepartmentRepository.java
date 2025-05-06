package falcuty.ntu.groupone.graduation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import falcuty.ntu.groupone.graduation.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
