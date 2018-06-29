package com.cgi.controller;

import com.cgi.component.UserServiceImpl;
import com.cgi.model.User;
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
    public Iterable<User> user() {
        return userService.findAll();
    }

    @PostMapping(value = "/users")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(value = "/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PutMapping(value = "/users/{id}")
    public User update(@PathVariable Long id, @RequestBody User originalUser) {
        return userService.update(id, originalUser);
    }

    @DeleteMapping(value = "/users/{id}")
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
