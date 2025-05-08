package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/student/dashboard")
    public String studentDashboard() {
        return "login/student_dashboard";
    }

    @GetMapping("/supervisor/dashboard")
    public String supervisorDashboard() {
        return "login/supervisor_dashboard";
    }
}
