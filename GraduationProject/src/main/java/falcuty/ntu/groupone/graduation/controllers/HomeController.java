package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.SupervisorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@Autowired
	private SupervisorService supervisorService;
	
	public HomeController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	@GetMapping("/")
	public String getHome(HttpServletRequest request, ModelMap model) {
		model.addAttribute("currentPath", request.getRequestURI());
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
}
