package falcuty.ntu.groupone.graduation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import falcuty.ntu.groupone.graduation.models.Student;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.StudentService;
import falcuty.ntu.groupone.graduation.services.SupervisorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private StudentService studentService;
	
	public HomeController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	@GetMapping("/")
	public String getHome(HttpServletRequest request, ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
		if (supervisorOpt.isPresent()) {
			model.addAttribute("type", "supervisor");
			model.addAttribute("name", supervisorOpt.get().getName());
		} else {
			Optional<Student> studentOpt = studentService.findStudentByEmail(email);
			if (studentOpt.isPresent()) {
				model.addAttribute("type", "student");
				model.addAttribute("name", studentOpt.get().getName());
		    } else {
		        model.addAttribute("name", "Người dùng không xác định");
		    }
		}
		model.addAttribute("email", email);
		model.addAttribute("currentPath", request.getRequestURI());
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/profile/{email}")
	public String getDetail(@PathVariable String email,
	                          @AuthenticationPrincipal UserDetails userDetails,
	                          ModelMap model) {
	    Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
	    Optional<Student> studentOpt = studentService.findStudentByEmail(email);

	    if (supervisorOpt.isPresent()) {
	        Supervisor supervisor = supervisorOpt.get();
	        boolean isOwner = userDetails != null && userDetails.getUsername().equals(supervisor.getEmail());
	        model.addAttribute("user", supervisor);
	        model.addAttribute("type", "supervisor");
	        model.addAttribute("isOwner", isOwner);
	        return "profile/view";
	    } else if (studentOpt.isPresent()) {
	        Student student = studentOpt.get();
	        boolean isOwner = userDetails != null && userDetails.getUsername().equals(student.getEmail());
	        model.addAttribute("user", student);
	        model.addAttribute("type", "student");
	        model.addAttribute("isOwner", isOwner);
	        return "profile/view";
	    } else {
	        throw new RuntimeException("User not found");
	    }
	}
	
	@GetMapping("/profile/edit")
	public String showEditForm(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
	    String username = userDetails.getUsername();

	    Supervisor supervisor = supervisorService
	        .findSupervisorByEmail(username)
	        .orElseThrow(() -> new IllegalArgumentException("Supervisor not found for username: " + username));

	    model.addAttribute("supervisor", supervisor);
	    return "supervisor/edit";
	}
	
	private boolean hasRole(UserDetails userDetails, String role) {
	    return userDetails.getAuthorities().stream()
	        .anyMatch(auth -> auth.getAuthority().equals("ROLE_" + role));
	}
}
