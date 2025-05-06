package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.SupervisorService;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
	@Autowired
	private SupervisorService supervisorService;
	
	public SupervisorController(SupervisorService supervisorService) {
		this.supervisorService = supervisorService;
	}
	
	@GetMapping("/detail/{id}")
	public String getDetail(@PathVariable Integer id,ModelMap model) {
		Supervisor supervisor = supervisorService.findSupervisorById(id).orElseThrow(() -> new RuntimeException("Supervisor not found with ID: " + id));;
		model.addAttribute(supervisor);
		return ("supervisor/index");
	}
}
