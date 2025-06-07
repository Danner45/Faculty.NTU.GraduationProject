package falcuty.ntu.groupone.graduation.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import falcuty.ntu.groupone.graduation.models.CountResearchTopic;
import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.Enrol;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
	@Autowired
	private CourseService courseService;
	@GetMapping("/home")
	public String getAdminHome(ModelMap model, @AuthenticationPrincipal UserDetails userDetails) {
		String email = userDetails.getUsername();
		Optional<Supervisor> supervisorOpt = supervisorService.findSupervisorByEmail(email);
		if (supervisorOpt.isPresent()) {
			List<ResearchTopic> researchTopics = researchTopicService.findAllByStateZero();
			model.addAttribute("projects",researchTopics);
		} else {
		    model.addAttribute("name", "Người dùng không xác định");
		}
		model.addAttribute("email", email);
		return "admin/index";
	}
	
	@GetMapping("/project/detail/{id}")
	public String getDetailProject(@PathVariable Integer id,
									@AuthenticationPrincipal UserDetails userDetails,
			 						ModelMap model) {
		String email = userDetails.getUsername();
		Supervisor supervisor = supervisorService.findSupervisorByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với email: " + email));
		ResearchTopic researchTopic = researchTopicService.findResearchTopicById(id);
		model.addAttribute("type", "supervisor");
		model.addAttribute("email", email);
        model.addAttribute("name", supervisor.getName());
        model.addAttribute("researchtopic", researchTopic);
        return "supervisor/project_detail";
	}
	
	@PostMapping("/project/accept/{id}")
	public String handleAcceptProject(@PathVariable Integer id,
									   ModelMap model) {
		ResearchTopic researchTopic = researchTopicService.findResearchTopicById(id);
		researchTopic.setState(1);
		researchTopicService.saveResearchTopic(researchTopic);
		return "redirect:/";
	}
}
