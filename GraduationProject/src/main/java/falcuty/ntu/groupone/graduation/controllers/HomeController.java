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
		Optional<Supervisor> supervisor = supervisorService.findSupervisorByEmail(email);
		model.addAttribute("supervisor", supervisor.get().getName());
		model.addAttribute("currentPath", request.getRequestURI());
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/profile")
	public String getDetail(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
		if (hasRole(userDetails, "SUPERVISOR")) {
	        Supervisor supervisor = supervisorService.findSupervisorByEmail(username)
	            .orElseThrow(() -> new RuntimeException("Supervisor not found"));
	        model.addAttribute("user", supervisor);
	        model.addAttribute("type", "supervisor");
	    } else if (hasRole(userDetails, "STUDENT")) {
	        Student student = studentService.findStudentByEmail(username)
	            .orElseThrow(() -> new RuntimeException("Student not found"));
	        model.addAttribute("user", student);
	        model.addAttribute("type", "student");
	    } else {
	        throw new AccessDeniedException("No access");
	    }
		return "detail";
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
