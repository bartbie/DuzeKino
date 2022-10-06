package org.duze.duzekino.service;

import org.duze.duzekino.exception.UserNotFoundException;
import org.duze.duzekino.model.User;
import org.duze.duzekino.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> result = userRepository.findUserByUsername(username);
        if (result.isPresent()) {
          return result.get();
        } throw new UserNotFoundException("User not found with username: " + username);
    }

    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } throw new UserNotFoundException("User not found with id: " + id);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if(count == null || count ==0) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}
