package cz.prague.js.home.invoice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @GetMapping("login")
    public String index(Principal principal,Model model) {
        logger.info("GetMapping login");

        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";

    }

    @GetMapping("/")
    public String index1(Principal principal,Model model) {
        logger.info("GetMapping /");
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @GetMapping("/signup")
    public String index2() {
        logger.info("GetMapping /signup");
        return "login";

    }

    @GetMapping("/login-error")
    public String error(Model model) {
    logger.info("GetMapping /error");

    model.addAttribute("loginError",true);
    return "login";
    }

}
