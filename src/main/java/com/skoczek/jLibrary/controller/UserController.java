package com.skoczek.jLibrary.controller;

import com.skoczek.jLibrary.model.User;
import com.skoczek.jLibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String userList(Model model) {

        model.addAttribute("user", userService.getUsers());

        return "users/user-list";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {

        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("searchedUser") String theName, Model model) {

        List<User> users = userService.searchUserByFirstName(theName);
        model.addAttribute("user", users);
        return "users/user-list";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register/new-register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult) {
        if (userService.isAlreadyRegistered(user.getEmail())) {
            bindResult.rejectValue("email", "email.exists", "Email already in database");
            return "register/new-register";
        } else if (bindResult.hasErrors())
            return "register/new-register";
        else {
            userService.addWithDefaultRole(user);
            return "register/register-success";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "redirect:/login";
    }

    @GetMapping("/admin/{id}/delete")
    public String deleteUserAsAdmin(@PathVariable Long id) {

        userService.deleteUser(id);

        return "redirect:/user/list";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, Model model) {

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "users/update-form";
    }
}
