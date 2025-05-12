package falcuty.ntu.groupone.graduation.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.model.Model;
import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/supervisors")
public class SupervisorController {
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
	@Autowired
	private CourseService courseService;
	
	public SupervisorController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	
	@GetMapping("/home")
	public String getHome(HttpServletRequest request, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
		if (supervisorOpt.isPresent()) {
			Optional<Course> course = courseService.findCourseById(63);
			List<ResearchTopic> researchTopics = researchTopicService.findAllTeacherResearchTopic(supervisorOpt.get(), true, course.get());
			model.addAttribute("type", "supervisor");
			model.addAttribute("name", supervisorOpt.get().getName());
			model.addAttribute("researchtopics",researchTopics);
		} else {
		    model.addAttribute("name", "Người dùng không xác định");
		}
		model.addAttribute("email", email);
		model.addAttribute("currentPath", request.getRequestURI());
		return "supervisor/index";
	}
	
	@GetMapping("/topics/all")
	public String getAllTopics() {
		return "supervisor/topic_list";
	}
}
