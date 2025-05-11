package falcuty.ntu.groupone.graduation.services.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.repositories.ICourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private ICourseRepository courseRepository;
	
	public List<Course> getLast4Courses() {
        return courseRepository.findTop4ByOrderByIdCourseDesc();
    }
	
	public Optional<Course> findCourseById(int id){
		return courseRepository.findById(id);
	}
}
