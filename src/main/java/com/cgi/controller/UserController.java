package com.cgi.controller;

import com.cgi.model.User;
import com.cgi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/users/{id}")
    public Optional<User> findOneUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping(value = "/users")
    public User saveOneUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping(value = "/users/{id}")
    public User updateOneUser(@PathVariable Long id, @RequestBody User originalUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        User modifiedUser = optionalUser.get();
        if (originalUser.getFirstName() != null) {
            modifiedUser.setFirstName(originalUser.getFirstName());
        }
        if (originalUser.getLastName() != null) {
            modifiedUser.setLastName(originalUser.getLastName());
        }
        if (originalUser.getEmail() != null) {
            modifiedUser.setEmail(originalUser.getEmail());
        }
        if (originalUser.getPhoneNumber() != null) {
            modifiedUser.setPhoneNumber(originalUser.getPhoneNumber());
        }
        userRepository.save(modifiedUser);
        return modifiedUser;
    }

    @DeleteMapping(value = "/users/{id}")
    void deleteOneUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
    }
}
