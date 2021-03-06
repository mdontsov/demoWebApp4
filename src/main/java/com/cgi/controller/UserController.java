package com.cgi.controller;

import com.cgi.dto.UserDTO;
import com.cgi.model.User;
import com.cgi.component.UserDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserDTOServiceImpl userDTOService;

    @Autowired
    public UserController(UserDTOServiceImpl userDTOService) {
        this.userDTOService = userDTOService;
    }

    @GetMapping(value = "/users")
    public List<UserDTO> findAllUsers() {
        return userDTOService.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public Optional<UserDTO> findOneUser(@PathVariable Long id) {
        return userDTOService.findOne(id);
    }

    @PostMapping(value = "/users")
    public UserDTO saveUser(@RequestBody User user) {
        return userDTOService.save(user);
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        return userDTOService.update(id, user);
    }

    @DeleteMapping(value = "/users/{id}")
    void deleteOneUser(@PathVariable Long id) {
        userDTOService.delete(id);
    }
}
