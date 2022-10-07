package org.duze.duzekino.service;

import lombok.RequiredArgsConstructor;
import org.duze.duzekino.model.User;
import org.duze.duzekino.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getUserByUsername(String username) throws UserNotFoundException{
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
        Optional<User> result = userRepository.findById(id);
        if(result.isEmpty()) {
            throw new UserNotFoundException("user not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}
