package org.duze.duzekino.controller;


import lombok.Getter;
import lombok.NonNull;
import org.duze.duzekino.model.Permission;
import org.duze.duzekino.model.User;
import org.duze.duzekino.model.UserCredentials;
import org.duze.duzekino.repository.UserRepository;
import org.duze.duzekino.service.UserNotFoundException;
import org.duze.duzekino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    User getUser() throws UserNotFoundException {

        return userService.getUser(2L);
    }

    @GetMapping("/username")
    User getUserByUsername() throws UserNotFoundException {
        return userService.getUserByUsername("admin");
    }

    @PostMapping("/login")
    ResponseEntity<User> authorize(@RequestBody UserCredentials userCredentials) throws UserNotFoundException {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        User user = userService.getUserByUsername(username);
        if (user.getPassword().equalsIgnoreCase(password)) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registration")
    ResponseEntity<User> register(@RequestBody User user) {
        user.setPermission(Permission.BASIC);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
