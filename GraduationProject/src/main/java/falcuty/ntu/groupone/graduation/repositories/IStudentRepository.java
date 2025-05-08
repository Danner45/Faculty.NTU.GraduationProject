package falcuty.ntu.groupone.graduation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import falcuty.ntu.groupone.graduation.models.Student;

public interface IStudentRepository extends JpaRepository<Student, String>{
	Optional<Student> findByEmail(String email);
}
