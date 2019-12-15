package cz.prague.js.home.invoice.controller;

import cz.prague.js.home.invoice.common.ControllerHelperEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {
    private static final String LOGIN_PAGE_TEMPLATE = "user/login";
    private static final String INVOICE_PAGE_TEMPLATE = "invoices/list";

    @GetMapping("/")
    public String rootPage(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:userIvoices";
        }
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), "home/homeNotSignedIn");
        return ControllerHelperEnum.BASE_PAGE.getName();
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), LOGIN_PAGE_TEMPLATE);
        return ControllerHelperEnum.BASE_PAGE.getName();
    }

    @GetMapping("/login-error")
    public String error(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), LOGIN_PAGE_TEMPLATE);
        return ControllerHelperEnum.BASE_PAGE.getName();
    }

}
