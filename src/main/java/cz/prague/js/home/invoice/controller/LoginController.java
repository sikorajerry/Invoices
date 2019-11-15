package cz.prague.js.home.invoice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("login")
    public String index(Principal principal,Model model) {
        model.addAttribute("module", "home");
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";

    }

    @GetMapping("/")
    public String index1(Principal principal,Model model) {
        model.addAttribute("module", "home");
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";

    }

    @GetMapping("/signup")
    public String index2(Principal principal,Model model) {
        return "login";

    }
}
