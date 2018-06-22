package com.cgi.controller;

import com.cgi.model.User;
import com.cgi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public Iterable<User> user() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public User save(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public User update(@PathVariable Long id, @RequestBody User originalUser) {
        Optional<User> optionalUser = userRepository.findById(id);
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
        userRepository.save(modifiedUser);
        return modifiedUser;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    void delete(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        userRepository.delete(user);
    }
}
