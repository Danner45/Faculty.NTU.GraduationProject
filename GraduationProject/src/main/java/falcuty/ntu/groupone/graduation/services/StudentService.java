package falcuty.ntu.groupone.graduation.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.repositories.IStudentRepository;

@Service
public class StudentService implements IStudentService{
	@Autowired
	private IStudentRepository studentRepository;

	@Override
	public Optional<Student> findStudentByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(String email, Student student) {
		return studentRepository.findByEmail(email)
	            .map(existing -> {
	                existing.setCv(student.getCv());
	                existing.setImgUrl(student.getImgUrl());
	                return studentRepository.save(existing);
	            })
	            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + email));
	}

	@Override
	public Optional<Student> findStudentById(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	
}
