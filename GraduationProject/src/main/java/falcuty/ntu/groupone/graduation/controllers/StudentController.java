package falcuty.ntu.groupone.graduation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.EnrolService;
import falcuty.ntu.groupone.graduation.services.implement.ProjectTypeService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.StudentService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@Autowired
	private EnrolService enrolService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/home")
	public String getHome(HttpServletRequest request, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
	    String email = userDetails.getUsername();
	    Optional<Student> studentOpt = studentService.findStudentByEmail(email);
	    
	    if (studentOpt.isPresent()) {
	        Student student = studentOpt.get();
	        model.addAttribute("type", "student");
	        model.addAttribute("name", student.getName());
	        model.addAttribute("email", student.getEmail());
	        model.addAttribute("img", student.getImgUrl());
	        model.addAttribute("avgGrade", student.getAvgGrade());
	        model.addAttribute("totalCredit", student.getTotal_credit());
	        
	        List<ResearchTopic> researchTopics = researchTopicService.findAllByStateZero(); 
	        model.addAttribute("researchtopics", researchTopics);
	    } else {
	        model.addAttribute("name", "Người dùng không xác định");
	    }

	    model.addAttribute("currentPath", request.getRequestURI());
	    return "student/index";
	}

	
}
