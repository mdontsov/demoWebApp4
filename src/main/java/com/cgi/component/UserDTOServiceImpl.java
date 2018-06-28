package com.cgi.component;

import com.cgi.config.MapperConfig;
import com.cgi.dto.UserDTO;
import com.cgi.model.User;
import com.cgi.repository.UserRepository;
import com.cgi.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDTOServiceImpl implements UserDTOService {

    private UserRepository userRepository;

    private MapperConfig mapperConfig;

    @Autowired
    public UserDTOServiceImpl(UserRepository userRepository, MapperConfig mapperConfig) {
        this.userRepository = userRepository;
        this.mapperConfig = mapperConfig;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> mapperConfig.convertToDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        return mapperConfig.convertToOptionalDto(userRepository.findById(id));
    }

    @Override
    public UserDTO save(User user) {
        return mapperConfig.convertToDto(userRepository.saveAndFlush(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO update(Long id, User user) {
        return mapperConfig.convertToDto(updateUser(id, user));
    }

    private User updateUser(Long id, User user) {
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
