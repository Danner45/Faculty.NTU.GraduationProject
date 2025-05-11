package falcuty.ntu.groupone.graduation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import falcuty.ntu.groupone.graduation.models.Course;

public interface ICourseRepository extends JpaRepository<Course, Integer>{
	List<Course> findTop4ByOrderByIdCourseDesc();
}
