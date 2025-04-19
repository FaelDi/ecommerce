package com.rafastech.media.ecommerce.mapper.impl;

import com.rafastech.media.ecommerce.dto.UserDTO;
import com.rafastech.media.ecommerce.entity.User;
import com.rafastech.media.ecommerce.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDTO> parseListDTO(List<User> objects) {
        return List.of();
    }

    @Override
    public List<User> parseListEntity(List<UserDTO> objectsDTO) {
        return List.of();
    }

    @Override
    public UserDTO parseDTO(User object) {
        return UserDTO.builder().name(object.getName()).email(object.getEmail()).build();
    }

    @Override
    public User parseEntity(UserDTO objectDTO) {
        return User.builder().name(objectDTO.getName()).email(objectDTO.getEmail()).build();
    }
}
