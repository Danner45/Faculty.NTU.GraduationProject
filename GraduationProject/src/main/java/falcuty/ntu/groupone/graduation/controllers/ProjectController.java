package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/project")
public class ProjectController {
	@GetMapping("/create")
	public String newProject(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
		
		return "new_project";
	}
}
