package com.cgi.service;

import com.cgi.dto.UserDTO;
import com.cgi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDTOService {

    List<UserDTO> findAll();

    Optional<UserDTO> findOne(Long id);

    UserDTO save(User user);

    void delete(Long id);
}
