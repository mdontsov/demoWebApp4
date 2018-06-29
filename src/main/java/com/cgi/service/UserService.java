package com.cgi.service;

import com.cgi.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findOne(Long id);

    User save(User user);

    void delete(Long id);

    User update(Long id, User user);
}
