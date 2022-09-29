package org.duze.duzekino.service;

import org.assertj.core.api.Assertions;
import org.duze.duzekino.model.Permission;
import org.duze.duzekino.model.User;
import org.duze.duzekino.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
@DataJpaTest
@Rollback(false)
public class UserServiceTest {

//    @Autowired
//    UserRepository userService;
//
//    @Test
//    public void testAddUser() {
//        User user = new User();
//        user.setUsername("username");
//        user.setPassword("password");
//        user.setPermission(Permission.BASIC);
//        user.setEmail("email");
//
//        User savedUser = userService.save(user);
//
//        org.assertj.core.api.Assertions.assertThat(savedUser).isNotNull();
//        org.assertj.core.api.Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
//    }
//
//    @Test
//    public void testListAll() {
//        Iterable<User> users = userService.findAll();
//        org.assertj.core.api.Assertions.assertThat(users).hasSizeGreaterThan(0);
//        for (User user : users
//        ) {
//            System.out.println(users);
//        }
//    }
//
//    @Test
//    public void testUpdate() {
//        List<User> users = (List<User>) userService.findAll();
//        User lastUser = users.get(users.size()-1);
//        Long userId = lastUser.getId();
//        Optional<User> optionalUser = userService.findById(userId);
//        User user = optionalUser.get();
//        String newEmail = "newEmailSet";
//        user.setEmail(newEmail);
//        userService.save(user);
//
//        User updatedUser = userService.findById(userId).get();
//        org.assertj.core.api.Assertions.assertThat(updatedUser.getEmail()).isEqualTo(newEmail);
//    }
//
//    @Test
//    public void testGet() {
//        List<User> users = (List<User>) userService.findAll();
//        User lastUser = users.get(users.size()-1);
//        Long userId = lastUser.getId();
//        Optional<User> optionalUser = userService.findById(userId);
//        org.assertj.core.api.Assertions.assertThat(optionalUser).isPresent();
//        System.out.println(optionalUser.get());
//    }
//
//    @Test
//    public void testDelete() {
//        List<User> users = (List<User>) userService.findAll();
//        User lastUser = users.get(users.size()-1);
//        Long userId = lastUser.getId();
//        Optional<User> optionalUser = userService.findById(userId);
//        userService.deleteById(userId);
//        Assertions.assertThat(optionalUser).isNotPresent();
//    }
}
