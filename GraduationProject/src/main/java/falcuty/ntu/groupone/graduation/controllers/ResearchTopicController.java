package falcuty.ntu.groupone.graduation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import falcuty.ntu.groupone.graduation.models.ResearchTopic;
import falcuty.ntu.groupone.graduation.services.implement.ResearchTopicService;

@Controller
public class ResearchTopicController {
	@Autowired
    private ResearchTopicService researchTopicService;

    @GetMapping("/my-topics")
    public String getMyTopics(Model model, @RequestParam("teacherId") Integer teacherId) {
        List<ResearchTopic> topics = researchTopicService.getTopicsByTeacherId(teacherId);
        model.addAttribute("topics", topics);
        return "my_topics"; // Trả về file `my_topics.html`
    }
}
