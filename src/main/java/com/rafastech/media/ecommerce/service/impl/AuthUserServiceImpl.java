package com.rafastech.media.ecommerce.service.impl;

import com.rafastech.media.ecommerce.entity.User;
import com.rafastech.media.ecommerce.mapper.AuthUserMapper;
import com.rafastech.media.ecommerce.dto.TokenDTO;
import com.rafastech.media.ecommerce.dto.UserInfoDTO;
import com.rafastech.media.ecommerce.dto.UserLoginDTO;
import com.rafastech.media.ecommerce.repository.UserRepository;
import com.rafastech.media.ecommerce.service.AuthUserService;
import com.rafastech.media.ecommerce.service.JWTService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    
    private final UserRepository userRepository;

    private final AuthUserMapper mapper;

    private final PasswordEncoder encoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthUserServiceImpl(UserRepository userRepository,
                               AuthUserMapper userMapper,
                               PasswordEncoder encoder,
                               JWTService jwtService,
                               AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.mapper = userMapper;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<UserLoginDTO> findAll() {
        return mapper.parseListDTO(userRepository.findAll());
    }

    @Override
    public Optional<UserLoginDTO> findById(UUID id) {
        Optional<User> authUserOp = userRepository.findById(id);
        if(authUserOp.isPresent()) {
            User usuario = authUserOp.get();
            return Optional.ofNullable(mapper.parseDTO(usuario));
        }

        throw new EntityNotFoundException();
    }

    @Override
    @Transactional
    public UserLoginDTO create(UserLoginDTO userLoginDTO) {
        var user = mapper.parseEntity(userLoginDTO);
        user.setPassword(encoder.encode(userLoginDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return mapper.parseDTO(user);
    }

    @Override
    public UserLoginDTO edit(UUID id, UserLoginDTO userLoginDTO) {
        Optional<User> authUserOp = userRepository.findById(id);

        if(authUserOp.isPresent()) {
            User usuario = authUserOp.get();
            usuario.setEmail(userLoginDTO.getEmail());
            usuario.setId(id);
            usuario = userRepository.save(usuario);
            return mapper.parseDTO(usuario);
        }

        throw new EntityNotFoundException();
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public TokenDTO login(UserLoginDTO usuarioLoginDTO) throws AuthenticationException, EntityNotFoundException {

        var autentication =
                new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(),usuarioLoginDTO.getPassword());

        authenticationManager.authenticate(autentication);

        var authUserOp = userRepository.findByEmail(usuarioLoginDTO.getEmail());

        if(authUserOp.isPresent()) {
                var authUser = authUserOp.get();
                var user = authUserOp.get();
                var userInfoDTO = UserInfoDTO.builder()
                        .id(user.getId())
                        .username(user.getEmail()).build();
                var token = jwtService.generateToken(userInfoDTO);

                var userLoginDTO = UserLoginDTO.builder();
                try {
                    userLoginDTO.email(authUser.getEmail());
                    userLoginDTO.id(authUser.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                return TokenDTO.builder()
                        .token(token)
                        .type("Bearer")
                        .build();
        }

        throw new EntityNotFoundException();
    }
}
