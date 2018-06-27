package com.cgi.config;

import com.cgi.dto.UserDTO;
import com.cgi.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Optional;

@Component
public class MapperConfig {

    @Bean
    private ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return new ModelMapper();
    }

    public UserDTO convertToDto(User user) {
        return modelMapper().map(user, UserDTO.class);
    }

    public Optional<UserDTO> convertToOptionalDto(Optional<User> user) {
        return Optional.of(modelMapper().map(user, UserDTO.class));
    }

    public User convertToEntity(UserDTO userDTO) throws ParseException {
        return modelMapper().map(userDTO, User.class);
    }
}
