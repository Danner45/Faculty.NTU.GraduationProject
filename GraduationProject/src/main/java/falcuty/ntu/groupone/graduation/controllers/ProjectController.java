package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/project")
public class ProjectController {
	@GetMapping("/create")
	public String newProject() {
		return "new_project";
	}
}
