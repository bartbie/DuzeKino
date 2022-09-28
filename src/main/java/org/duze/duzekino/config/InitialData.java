package org.duze.duzekino.config;

import org.duze.duzekino.model.Permission;
import org.duze.duzekino.model.User;
import org.duze.duzekino.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class InitialData {

    @Autowired
    private UserService userService;

    //@PostConstruct
    public void initialiseDatabase() {

        User admin = new User("admin2", "1234", "admin@admin.com", Permission.ADMIN);

        userService.saveUser(admin);

    }
}
