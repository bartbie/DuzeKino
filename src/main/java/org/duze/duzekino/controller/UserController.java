package org.duze.duzekino.controller;


import lombok.Getter;
import org.duze.duzekino.model.User;
import org.duze.duzekino.repository.UserRepository;
import org.duze.duzekino.service.UserNotFoundException;
import org.duze.duzekino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//The @controller annotation indicated that a particular class serves the role of a controller.
//The controller class is responsible for processing incoming HTTP requests, preparing a model,
// and returning the view to be rendered as a response.
@RestController
public class UserController {

    //@Autowired enables us to inject object dependency implicitly. It internally uses setter or constructor injection.
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    //This annotation is used to map HTTP requests onto a specific handler method (can also be a class)
    //the url is the place this specific method will be executed.
    @GetMapping("/")
    public User getUser()throws UserNotFoundException{
        //List<User> lstUser = userService.getAllUsers();
        User user = userService.getUser(1L);
        return user;
    }
}
