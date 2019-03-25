package com.skoczek.demo.controller;

import com.skoczek.demo.model.User;
import com.skoczek.demo.service.BookService;
import com.skoczek.demo.service.UserService;

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

    @Autowired
    private UserService userService;


    @Autowired
    private BookService bookService;


    @GetMapping("/list")
    public String userList(Model model){

        model.addAttribute("user", userService.getUsers());

        return "users/user-list";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user){

        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/showAddForm")
    public String showAddUserForm(Model model){

        User user = new User();
        model.addAttribute("user", user);

        return "users/user-form";
    }

    @GetMapping("/search")
    public String searchByTitle(@RequestParam("searchedUser") String theName, Model model){

        List<User> users = userService.searchUserByFirstName(theName);
        model.addAttribute("user", users);
        return "users/user-list";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register/register-form";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult) {
        if(bindResult.hasErrors())
            return "register/register-form";
        else {
            userService.addWithDefaultRole(user);
            return "register/register-success";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return "redirect:/user/register";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, Model model){

        User user = userService.findById(id);
        model.addAttribute("user", user);

        return "users/update-form";
    }
}
