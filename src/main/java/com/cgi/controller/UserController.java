package com.cgi.controller;

import com.cgi.config.MapperConfig;
import com.cgi.dto.UserDTO;
import com.cgi.model.User;
import com.cgi.component.UserDTOServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDTOServiceImpl userService;

    private MapperConfig mapperConfig;

    @Autowired
    public UserController(UserDTOServiceImpl userService, MapperConfig mapperConfig) {
        this.userService = userService;
        this.mapperConfig = mapperConfig;
    }

    @GetMapping(value = "/users")
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/users/{id}")
    public UserDTO findOneUser(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PostMapping(value = "/users")
    public UserDTO saveOneUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO updateOneUser(@PathVariable Long id, @RequestBody User user) {
        UserDTO userDto = findOneUser(id);
        if (user.getFirstname() != null) {
            userDto.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            userDto.setLastname(user.getLastname());
        }
        if (user.getEmail() != null) {
            userDto.setEmail(user.getEmail());
        }
        if (user.getPhonenumber() != null) {
            userDto.setPhonenumber(user.getPhonenumber());
        }
        userService.save(user);
        return userDto;
    }

    @DeleteMapping(value = "/users/{id}")
    void deleteOneUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
