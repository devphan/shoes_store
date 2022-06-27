package com.ecommerce.shoes.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.shoes.entity.User;
import com.ecommerce.shoes.model.dto.UserDto;
import com.ecommerce.shoes.model.request.UserReq;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(UserReq req) {
        // hash password
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        // convert req -> entity
        User user = mapper.map(req, User.class);
        // set default Role
        user.setRole("USER");
        return user;
    }

    public UserDto toUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }

}
