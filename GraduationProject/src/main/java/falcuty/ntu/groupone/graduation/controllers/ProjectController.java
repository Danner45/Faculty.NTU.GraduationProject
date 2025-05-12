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
	
	@GetMapping("/create")
    public String newProject(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
        String email = userDetails.getUsername();

        Supervisor supervisor = supervisorService.findSupervisorByEmail(email)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với email: " + email));
        
        List<ProjectType> projectTypes = projectTypeService.getAllProjectTypes();
        List<Course> courses = courseService.getLast4Courses();
        model.addAttribute("type", "supervisor");
        model.addAttribute("project", new ResearchTopic());
        model.addAttribute("email", email);
        model.addAttribute("name", supervisor.getName());
        model.addAttribute("projectTypes", projectTypes);
        model.addAttribute("courses", courses);
        return "supervisor/project_new";
    }
	
	@PostMapping("/add")
    public String handleCreateProject(@ModelAttribute ResearchTopic project,
                                      @RequestParam("projectType") Integer typeId,
                                      @RequestParam("course") Integer courseId,
                                      @RequestParam("isResearch") Integer isResearch,
                                      @AuthenticationPrincipal UserDetails userDetails) {
		Optional<Supervisor> supervisor = supervisorService.findSupervisorByEmail(userDetails.getUsername());
        Optional<ProjectType> type = projectTypeService.findProjectTypeById(typeId);
        Optional<Course> course = courseService.findCourseById(courseId);
        
        project.setProjectType(type.get());
        project.setCourse(course.get());
        project.setIsResearch(isResearch == 1);
        project.setTeacherCreated(supervisor.get());
        project.setState(0);
        project.setMaxJoin(1);

        researchTopicService.addResearchTopic(project);
        return "redirect:/supervisors/home";
    }
	
	@GetMapping("/detail/{id}")
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
        return "project_detail";
	}
}
