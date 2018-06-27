package com.cgi.component;

import com.cgi.config.MapperConfig;
import com.cgi.dto.UserDTO;
import com.cgi.model.User;
import com.cgi.repository.UserRepository;
import com.cgi.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
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
        return mapperConfig.convertToDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
