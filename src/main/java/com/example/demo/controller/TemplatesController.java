package com.example.demo.controller;

import com.example.demo.user.User;
import com.example.demo.user.UserNotFound;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class TemplatesController {
    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("api/v1/students")
    public String getCourses() {
        return "index";
    }

    @GetMapping("permission")
    public String getPermission() {
        return "permission";
    }
    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("pageTitle", "Add New User");
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        userService.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user = userService.get(id);
            model.addAttribute("pageTitle", "Edit User id: " + id);
            model.addAttribute("user", user);
            return "user_form";
        } catch (UserNotFound e) {
            ra.addFlashAttribute("message", "User with id " + id + " not found");
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra){
        try {
            userService.delete(id);
            ra.addFlashAttribute("message", "The user with id: " + id + " has been deleted successfully!");
        } catch (UserNotFound e) {
            ra.addFlashAttribute("message", "User with id " + id + " not found");
        }
        return "redirect:/users";
    }
}
