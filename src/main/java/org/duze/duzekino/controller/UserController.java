package org.duze.duzekino.controller;

import org.duze.duzekino.model.User;
import org.duze.duzekino.model.UserCredentials;
import org.duze.duzekino.repository.UserRepository;
import org.duze.duzekino.service.UserNotFoundException;
import org.duze.duzekino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    User getUser()throws UserNotFoundException {

        return userService.getUser(2L);
    }

    @GetMapping("/username")
    User getUserByUsername() throws UserNotFoundException {
        return userService.getUserByUsername("admin");
    }

    @PostMapping("/userCredentialsSubmit")
    String authorize(@RequestBody UserCredentials userCredentials) throws UserNotFoundException {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        User user = userService.getUserByUsername(username);
        if (user.getPassword().equalsIgnoreCase(password)) {
            return "authorize";
        }
            return "nope";
    }

}
