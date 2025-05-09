package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import falcuty.ntu.groupone.graduation.models.Supervisor;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHome(HttpServletRequest request, ModelMap model) {
		model.addAttribute("currentPath", request.getRequestURI());
		return "index";
	}
}
