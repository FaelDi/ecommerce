package com.rafastech.media.ecommerce.service.impl;

import com.rafastech.media.ecommerce.dto.UserDTO;
import com.rafastech.media.ecommerce.mapper.UserMapper;
import com.rafastech.media.ecommerce.repository.UserRepository;
import com.rafastech.media.ecommerce.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> findAll() {
        return userMapper.parseListDTO(userRepository.findAll());
    }

    @Override
    public Optional<UserDTO> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public UserDTO create(UserDTO obj) {
        return null;
    }

    @Override
    public UserDTO edit(UUID id, UserDTO obj) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
