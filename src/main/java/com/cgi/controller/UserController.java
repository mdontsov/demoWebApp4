package com.cgi.controller;

import com.cgi.model.User;
import com.cgi.repository.UserRepository;
import com.cgi.component.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public Iterable<User> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/users/{id}")
    public Optional<User> findOneUser(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PostMapping(value = "/users")
    public User saveOneUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping(value = "/users/{id}")
    public User updateOneUser(@PathVariable Long id, @RequestBody User originalUser) {
        Optional<User> optionalUser = userService.findOne(id);
        User modifiedUser = optionalUser.get();
        if (originalUser.getFirstname() != null) {
            modifiedUser.setFirstname(originalUser.getFirstname());
        }
        if (originalUser.getLastname() != null) {
            modifiedUser.setLastname(originalUser.getLastname());
        }
        if (originalUser.getEmail() != null) {
            modifiedUser.setEmail(originalUser.getEmail());
        }
        if (originalUser.getPhonenumber() != null) {
            modifiedUser.setPhonenumber(originalUser.getPhonenumber());
        }
        userService.save(modifiedUser);
        return modifiedUser;
    }

    @DeleteMapping(value = "/users/{id}")
    void deleteOneUser(@PathVariable Long id) {
        Optional<User> user = userService.findOne(id);
        userService.delete(id);
    }
}
