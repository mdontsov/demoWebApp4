package com.cgi.config;

import com.cgi.dto.UserDTO;
import com.cgi.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class MapperConfig {

    @Bean
    private ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public UserDTO convertToDto(User user) {
        return modelMapper().map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDTO) throws ParseException {
        return modelMapper().map(userDTO, User.class);
    }
}