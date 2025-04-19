package com.rafastech.media.ecommerce.controller;

import com.rafastech.media.ecommerce.dto.UserDTO;
import com.rafastech.media.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<UserDTO, UserService> {
    public UserController(UserService service) {
        super(service);
    }
}
