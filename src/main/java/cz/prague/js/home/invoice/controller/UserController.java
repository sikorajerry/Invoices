package cz.prague.js.home.invoice.controller;

import cz.prague.js.home.invoice.dto.UserDto;
import cz.prague.js.home.invoice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users/")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;



    @GetMapping("home")
    public String welcomeHome() {
        logger.info("GetMapping home");
        return "redirect:index";

    }

    @GetMapping("add")
    public String addUser(Model model) {
        logger.info("GetMapping add");
        model.addAttribute(new UserDto());
        return "user/add_user";

    }

    @PostMapping("add")
    public String addUser(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model) {
        logger.info("PostMapping add");
        if (result.hasErrors()) {
            return "user/add_user";
        }

        try {
            userService.save(userDto);
        } catch (Exception e) {
            model.addAttribute("userExist", true);
            return "user/add_user";

        }
        return "redirect:list";
    }

    @PostMapping("delete")
    public String handleDeleteUser(String id) {
        logger.info("PostMapping delete");
        userService.delete(id);
        return "redirect:list";
    }

    @GetMapping("list")
    public String list(Model model) {
        logger.info("GetMapping list");
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

}
