package com.rafastech.media.ecommerce.mapper.impl;

import com.rafastech.media.ecommerce.dto.UserDTO;
import com.rafastech.media.ecommerce.dto.UserLoginDTO;
import com.rafastech.media.ecommerce.entity.User;
import com.rafastech.media.ecommerce.mapper.AuthUserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthMapperImpl implements AuthUserMapper {

    @Override
    public List<UserLoginDTO> parseListDTO(List<User> objects) {
        return List.of();
    }

    @Override
    public List<User> parseListEntity(List<UserLoginDTO> objectsDTO) {
        return List.of();
    }

    @Override
    public UserLoginDTO parseDTO(User object) {
        return UserLoginDTO.builder().id(object.getId()).email(object.getEmail()).name(object.getName()).build();
    }

    @Override
    public User parseEntity(UserLoginDTO objectDTO) {
        return User.builder().id(objectDTO.getId()).email(objectDTO.getEmail()).name(objectDTO.getName()).build();
    }
}
