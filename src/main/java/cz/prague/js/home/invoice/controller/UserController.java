package cz.prague.js.home.invoice.controller;

import cz.prague.js.home.invoice.dto.UserDto;
import cz.prague.js.home.invoice.service.UserService;
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


    @Autowired
    private UserService userService;



    @GetMapping("home")
    public String welcomeHome() {
        return "redirect:index";

    }

    @GetMapping("add")
    public String addUser(Model model) {
        model.addAttribute(new UserDto());
        return "user/add_user";

    }

    @PostMapping("add")
    public String addUser(@Valid @ModelAttribute UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "user/add_user";
        }

        userService.save(userDto);
        return "redirect:list";
    }

    @PostMapping("delete")
    public String handleDeleteUser(String id) {

        userService.delete(id);
        return "redirect:list";
    }

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

}
