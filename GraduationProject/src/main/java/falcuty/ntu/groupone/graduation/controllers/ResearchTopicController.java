package falcuty.ntu.groupone.graduation.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.models.Supervisor;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;

@Controller
public class ResearchTopicController {
	@Autowired
    private ResearchTopicService researchTopicService;

    @GetMapping("/my_topics")
    public String getMyTopics(Model model, Principal principal) {
        String email = principal.getName();

        Supervisor teacher = researchTopicService.findSupervisorByEmail(email);
        List<ResearchTopic> topics = researchTopicService.getTopicsByTeacherId(teacher.getId());

        model.addAttribute("topics", topics);
        model.addAttribute("name", teacher.getName());
        model.addAttribute("email", email);

        return "/supervisor/my_topics";

    }
}
