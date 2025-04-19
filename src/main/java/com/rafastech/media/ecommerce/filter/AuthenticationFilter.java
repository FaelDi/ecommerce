package com.rafastech.media.ecommerce.filter;


import com.rafastech.media.ecommerce.entity.User;
import com.rafastech.media.ecommerce.service.AuthService;
import com.rafastech.media.ecommerce.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenString = request.getHeader("Authorization");

        if(tokenString!=null) {
            tokenString = tokenString.replace("Bearer ","");
            if(jwtService.validToken(tokenString)) {
                String username = jwtService.getUsernameByToken(tokenString);
                if(!username.isBlank() && SecurityContextHolder.getContext().getAuthentication()==null) {
                    User authUser = (User) authService.loadUserByUsername(username);
                    var user = authService.loadRealUserByUsername(username);
                    UsernamePasswordAuthenticationToken autentication =
                            new UsernamePasswordAuthenticationToken(authUser.getUsername(),user.getUserInfo(),authUser.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(autentication);
                }
            }
        }

        filterChain.doFilter(request, response);

    }

}