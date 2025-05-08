package falcuty.ntu.groupone.graduation.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String loginPage() {
        return "login/login"; // trả về login.html
    }

    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model) {
        model.addAttribute("userEmail", authentication.getName());
        return "login/home"; // trả về home.html
    }
}
