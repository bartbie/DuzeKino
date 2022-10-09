package org.duze.duzekino.service;

import org.assertj.core.api.Assertions;
import org.duze.duzekino.model.Permission;
import org.duze.duzekino.model.User;
import org.duze.duzekino.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepo;

    @BeforeEach
    void setUp() {
        User user = new User("hhh", "000", "www", Permission.BASIC);
        userRepo.deleteAll();
        userRepo.save(user);
    }

    @Test
    public void testAddUser() throws UserNotFoundException {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setPermission(Permission.BASIC);
        user.setEmail("email");

        userService.saveUser(user);
        User savedUser = userService.getUserByUsername("username");
        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void testListAll() {
        Iterable<User> users = userService.getAllUsers();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users
        ) {
            System.out.println(users);
        }
    }

    @Test
    public void testUpdate() throws UserNotFoundException {
        List<User> users = (List<User>) userService.getAllUsers();
        User lastUser = users.get(users.size() - 1);
        Long userId = lastUser.getId();
        User user = userService.getUser(userId);
        String newEmail = "newEmailSet";
        user.setEmail(newEmail);
        userService.saveUser(user);

        User updatedUser = userService.getUser(userId);
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo(newEmail);
    }

    @Test
    public void testGet() throws UserNotFoundException {
        List<User> users = userService.getAllUsers();
        User lastUser = users.get(users.size() - 1);
        Long userId = lastUser.getId();
        User user = userService.getUser(userId);
        Assertions.assertThat(user).isNotNull();
        System.out.println(user);
    }

    @Test
    public void testDelete() throws UserNotFoundException {
        List<User> users = (List<User>) userService.getAllUsers();
        User lastUser = users.get(users.size() - 1);
        Long userId = lastUser.getId();
        userService.deleteUser(userId);
        Optional<User> optional = userRepo.findById(userId);
        Assertions.assertThat(optional).isNotPresent();
    }
}