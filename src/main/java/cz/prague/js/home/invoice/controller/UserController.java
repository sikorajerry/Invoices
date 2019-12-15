package cz.prague.js.home.invoice.controller;

import cz.prague.js.home.invoice.common.ControllerHelperEnum;
import cz.prague.js.home.invoice.dto.UserDto;
import cz.prague.js.home.invoice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
    private static final String REGISTER_USER_HTML = "user/register";
    private static final String INVOICE_LIST_HTML = "invoices/list";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registerUser")
    public String addUser(Model model) {
        model.addAttribute(new UserDto());
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName() , REGISTER_USER_HTML);
        return ControllerHelperEnum.BASE_PAGE.getName();
    }

    @PostMapping("saveUser")
    public String addUser(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model) {

        //zde zachytavam chyby z BindingResult a vracim zpet na registracni formular
        if (result.hasErrors()) {
            model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), REGISTER_USER_HTML);
            return ControllerHelperEnum.BASE_PAGE.getName();
        }
        try {
            userService.save(userDto);
        } catch (Exception e) {
            //Vyhazuji exception kdyz uzivatel existuje , proto nastavuji chybu userExist
            // a vracim zpet registracni formular

            model.addAttribute("userExist", true);
            model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), REGISTER_USER_HTML);
            return ControllerHelperEnum.BASE_PAGE.getName();
        }
        model.addAttribute(ControllerHelperEnum.TEMPLATE.getName(), INVOICE_LIST_HTML);
        return ControllerHelperEnum.BASE_PAGE.getName();
    }

    @DeleteMapping("deleteUser")
    public String deleteUser(String id) {
        userService.delete(id);
        return "redirect:list";
    }

    @GetMapping("listAllUsers")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }
}
