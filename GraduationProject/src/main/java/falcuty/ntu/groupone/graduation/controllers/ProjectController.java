package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.SupervisorService;


@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private SupervisorService supervisorService;
	
	@GetMapping("/create")
    public String newProject(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
        String email = userDetails.getUsername();

        Supervisor supervisor = supervisorService.findSupervisorByEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với email: " + email));
        model.addAttribute("project", new ResearchTopic());
        model.addAttribute("email", email);
        model.addAttribute("name", supervisor.getName());
        return "new_project";
    }
}
