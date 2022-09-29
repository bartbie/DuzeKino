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
import java.util.Optional;

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
    ResponseEntity<User> authorize(@RequestBody User user) throws UserNotFoundException {
        String username = user.getUsername();
        String password = user.getPassword();
        User userFromDatabase = userService.getUserByUsername(username);
        if (userFromDatabase.getPassword().equalsIgnoreCase(password)) {
            return new ResponseEntity<>(userFromDatabase, HttpStatus.OK);
        }
        return new ResponseEntity<>(userFromDatabase, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registration")
    ResponseEntity<User> register(@RequestBody User user) {
        String username = user.getUsername();
        try {
            User userFromDatabase = userService.getUserByUsername(username);
            return new ResponseEntity<>(new User(), HttpStatus.NOT_FOUND);
        } catch (UserNotFoundException e) {
            user.setPermission(Permission.BASIC);
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
