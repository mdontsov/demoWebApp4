package com.cgi.component;

import com.cgi.model.User;
import com.cgi.repository.UserRepository;
import com.cgi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User update(Long id, User user) {
        Optional<User> foundUser = userRepository.findById(id);
        User updatedUser = foundUser.get();
        if (user.getFirstname() != null) {
            updatedUser.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            updatedUser.setLastname(user.getLastname());
        }
        if (user.getEmail() != null) {
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getPhonenumber() != null) {
            updatedUser.setPhonenumber(user.getPhonenumber());
        }
        userRepository.save(updatedUser);
        return updatedUser;
    }
}
