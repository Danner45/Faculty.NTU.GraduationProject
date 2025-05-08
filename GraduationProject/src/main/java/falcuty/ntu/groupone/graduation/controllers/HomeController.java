package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import falcuty.ntu.groupone.graduation.models.Supervisor;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String getHome() {
		return "index";
	}
}
