package com.cgi.controller;

import com.cgi.config.MapperConfig;
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

    private MapperConfig mapperConfig;

    @Autowired
    public UserController(UserDTOServiceImpl userService, MapperConfig mapperConfig) {
        this.userDTOService = userService;
        this.mapperConfig = mapperConfig;
    }

    @GetMapping(value = "/users")
    public List<UserDTO> findAllUsers() {
        return userDTOService.findAll();
    }

    @PostMapping(value = "/users/{id}")
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
