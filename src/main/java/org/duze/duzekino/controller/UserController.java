package org.duze.duzekino.controller;


import org.duze.duzekino.model.User;
import org.duze.duzekino.service.UserNotFoundException;
import org.duze.duzekino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//The @controller annotation indicated that a particular class serves the role of a controller.
//The controller class is responsible for processing incoming HTTP requests, preparing a model,
// and returning the view to be rendered as a response.
@Controller
public class UserController {

    //@Autowired enables us to inject object dependency implicitly. It internally uses setter or constructor injection.
    @Autowired
    UserService userService;

    //This annotation is used to map HTTP requests onto a specific handler method (can also be a class)
    //the url is the place this specific method will be executed.
    @RequestMapping("/user/add")
    public String addUser(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("user", userList);
        model.addAttribute("user", new User());
        return "users/userForm";
    }

    // handles the HTTP POST request matched with the given URL expression
    @PostMapping("/user/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "User was saved!");
        return "redirect:/manageusers";
    }

    @RequestMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            return "users/userForm";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            model.addAttribute("pageTitle", "edit user (ID: " + id + ")");
            return "redirect:/manageusers";
        }
    }

    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "User was deleted!");
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("message", "Can't delete user connected to active booking!");
        }

        return "redirect:/manageusers";
    }
}
