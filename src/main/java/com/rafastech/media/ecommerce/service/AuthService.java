package com.rafastech.media.ecommerce.service;

import com.rafastech.media.ecommerce.entity.User;
import com.rafastech.media.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AuthService implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuario = userRepository.findByEmail(email);
        if(usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }

    public User loadRealUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> usuario = userRepository.findByEmail(email);
        if(usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }

}