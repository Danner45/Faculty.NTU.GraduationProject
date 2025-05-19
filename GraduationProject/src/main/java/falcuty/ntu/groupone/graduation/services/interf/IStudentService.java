package falcuty.ntu.groupone.graduation.services.interf;

import java.util.List;
import java.util.Optional;

import falcuty.ntu.groupone.graduation.models.Student;

public interface IStudentService {
	List<Student> getAllStudents();
	Student saveStudent(String id,Student student);
	Optional<Student> findStudentById(String id);
	Optional<Student> findStudentByEmail(String email);
}
