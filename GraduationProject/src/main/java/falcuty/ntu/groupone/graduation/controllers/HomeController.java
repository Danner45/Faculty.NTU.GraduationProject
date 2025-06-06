package falcuty.ntu.groupone.graduation.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.EnrolService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.StudentService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
	@Autowired
	private CourseService courseService;
	
	
	
	public HomeController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	@GetMapping("/")
	public String getHome(HttpServletRequest request, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
		model.addAttribute("email", email);
		model.addAttribute("currentPath", request.getRequestURI());
		if (supervisorOpt.isPresent()) {
			Optional<Course> course = courseService.findCourseById(63);
			List<ResearchTopic> researchTopics = researchTopicService.findAllTeacherResearchTopic(supervisorOpt.get(), true, course.get());
			model.addAttribute("type", "supervisor");
			model.addAttribute("name", supervisorOpt.get().getName());
			model.addAttribute("researchtopics",researchTopics);
			if(supervisorOpt.get().getPosition() == 0) return "redirect:/admin/home";
			return "redirect:/supervisors/home";
		} else {
			Optional<Student> studentOpt = studentService.findStudentByEmail(email);
			if (studentOpt.isPresent()) {
				model.addAttribute("type", "student");
				model.addAttribute("name", studentOpt.get().getName());
		    } else {
		        model.addAttribute("name", "Người dùng không xác định");
		    }
			return "redirect:/students/home";
		}
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/profile/{email}")
	public String getDetail(@PathVariable String email,
	                          @AuthenticationPrincipal UserDetails userDetails,
	                          ModelMap model) {
		String ownEmail = userDetails.getUsername();
		Optional<Supervisor> ownSupervisor = supervisorService.findSupervisorByEmail(ownEmail);
		Optional<Student> ownStudent = studentService.findStudentByEmail(ownEmail);
	    Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
	    Optional<Student> studentOpt = studentService.findStudentByEmail(email);
	    
        if (ownSupervisor.isPresent()) {
        	if(supervisorOpt.isPresent()) {
        		boolean isOwner = userDetails != null && userDetails.getUsername().equals(supervisorOpt.get().getEmail());
        		model.addAttribute("isOwner", isOwner);
        	}
        	if(ownSupervisor.get().getPosition() == 0) {
        		model.addAttribute("ownType", "admin");
        	}
        	else {
        		model.addAttribute("ownType", "supervisor");
        	}
        	
        	model.addAttribute("ownUser", ownSupervisor.get());
        	
        }
        else if (ownStudent.isPresent()) {
        	if(studentOpt.isPresent()) {
        		boolean isOwner = userDetails != null && userDetails.getUsername().equals(studentOpt.get().getEmail());
        		model.addAttribute("isOwner", isOwner);
        	}
        	
        	model.addAttribute("ownType", "student");
        	model.addAttribute("ownUser", ownStudent.get());
        	
        }
	    if (supervisorOpt.isPresent()) {
	        Supervisor supervisor = supervisorOpt.get();
	        model.addAttribute("type", "supervisor");
	        model.addAttribute("user", supervisor);
	        
	    } else if (studentOpt.isPresent()) {
	        Student student = studentOpt.get();
	        
	        model.addAttribute("user", student);
	        model.addAttribute("type", "student");
	    } else {
	        throw new RuntimeException("User not found");
	    }
	    model.addAttribute("email", email);
	    return "detail";
	}
	
	@GetMapping("/profile/edit")
	public String showEditForm(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
	    String email = userDetails.getUsername();

	    Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
	    if (supervisorOpt.isPresent()) {
	        model.addAttribute("user", supervisorOpt.get());
	        model.addAttribute("type", "supervisor");
	        return "edit";
	    }

	    Optional<Student> studentOpt = studentService.findStudentByEmail(email);
	    if (studentOpt.isPresent()) {
	        model.addAttribute("user", studentOpt.get());
	        model.addAttribute("type", "student");
	        return "edit";
	    }
	    model.addAttribute("email", email);
	    throw new IllegalArgumentException("User not found for email: " + email);
	}
	
	private boolean hasRole(UserDetails userDetails, String role) {
	    return userDetails.getAuthorities().stream()
	        .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + role));
	}
	
	@PostMapping("/profile/update")
	public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
	                            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
	                            @RequestParam(value = "imgUrl", required = false) String existingImgUrl,
	                            @RequestParam("cv") String cv) throws IOException {

	    String email = userDetails.getUsername();

	    Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
	    if (supervisorOpt.isPresent()) {
	        Supervisor supervisor = supervisorOpt.get();
	        supervisor.setCv(cv);
	        if (imageFile != null && !imageFile.isEmpty()) {
	            String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
	            String uploadDir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	            Files.createDirectories(Paths.get(uploadDir));
	            Path path = Paths.get(uploadDir, filename);
	            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	            supervisor.setImgUrl("/image/" + filename);
	        } else {
	            supervisor.setImgUrl(existingImgUrl);
	        }
	        supervisorService.saveSupervisor(email, supervisor);
	        return "redirect:/profile/" + email;
	    }

	    Optional<Student> studentOpt = studentService.findStudentByEmail(email);
	    if (studentOpt.isPresent()) {
	        Student student = studentOpt.get();
	        student.setCv(cv);
	        if (imageFile != null && !imageFile.isEmpty()) {
	            String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
	            String uploadDir = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	            Files.createDirectories(Paths.get(uploadDir));
	            Path path = Paths.get(uploadDir, filename);
	            Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	            student.setImgUrl("/image/" + filename);
	        } else {
	            student.setImgUrl(existingImgUrl);
	        }
	        studentService.saveStudent(email, student);
	        return "redirect:/profile/" + email;
	    }

	    throw new IllegalArgumentException("Không tìm thấy người dùng để cập nhật.");
	}

}
