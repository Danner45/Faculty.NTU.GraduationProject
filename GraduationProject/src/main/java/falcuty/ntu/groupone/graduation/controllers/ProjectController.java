package falcuty.ntu.groupone.graduation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import falcuty.ntu.groupone.graduation.models.Course;
import falcuty.ntu.groupone.graduation.models.ProjectType;
import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.CourseService;
import falcuty.ntu.groupone.graduation.services.implement.ProjectTypeService;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;
import falcuty.ntu.groupone.graduation.services.implement.SupervisorService;


@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ResearchTopicService researchTopicService;
	
}
