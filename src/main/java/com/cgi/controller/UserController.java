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

    //    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @GetMapping(value = "/users")
    public Iterable<User> user() {
        return userService.findAll();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @PostMapping(value = "/users")
    public User save(@RequestBody User user) {
        userService.save(user);
        return user;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    @GetMapping(value = "/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return userService.findOne(id);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    @PutMapping(value = "/users/{id}")
    public User update(@PathVariable Long id, @RequestBody User originalUser) {
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

//    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    @DeleteMapping(value = "/users/{id}")
    void delete(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findOne(id);
        User user = optionalUser.get();
        userService.delete(user);
    }
}
