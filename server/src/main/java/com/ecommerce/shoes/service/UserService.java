package com.ecommerce.shoes.service;

import com.ecommerce.shoes.model.dto.UserDto;
import com.ecommerce.shoes.model.request.UserReq;

public interface UserService {
    UserDto register (UserReq req);

    String login(UserReq req);
}
