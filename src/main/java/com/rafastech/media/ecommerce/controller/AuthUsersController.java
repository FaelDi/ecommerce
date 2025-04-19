
package com.rafastech.media.ecommerce.controller;

import com.rafastech.media.ecommerce.dto.TokenDTO;
import com.rafastech.media.ecommerce.dto.UserLoginDTO;
import com.rafastech.media.ecommerce.service.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authUsers")
public class AuthUsersController extends BaseController<UserLoginDTO, AuthUserService>{


    public AuthUsersController(AuthUserService service) {
        super(service);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        try {

            return ResponseEntity.ok(service.login(userLoginDTO));

        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
