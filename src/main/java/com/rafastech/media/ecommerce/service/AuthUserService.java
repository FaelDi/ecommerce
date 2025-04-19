package com.rafastech.media.ecommerce.service;

import com.rafastech.media.ecommerce.dto.TokenDTO;
import com.rafastech.media.ecommerce.dto.UserLoginDTO;

public interface AuthUserService extends BaseService<UserLoginDTO>{
    TokenDTO login(UserLoginDTO usuarioDTO);
}
