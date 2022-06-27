package com.ecommerce.shoes.service.impl;

import com.ecommerce.shoes.entity.User;
import com.ecommerce.shoes.exception.DuplicateRecordException;
import com.ecommerce.shoes.exception.InternalServerException;
import com.ecommerce.shoes.model.dto.UserDto;
import com.ecommerce.shoes.model.mapper.UserMapper;
import com.ecommerce.shoes.model.request.UserReq;
import com.ecommerce.shoes.repository.UserRepository;
import com.ecommerce.shoes.service.UserService;
import com.ecommerce.shoes.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDto register(UserReq req) {
        User find = userRepository.findByEmail(req.getEmail());
        if (find != null)
            throw new DuplicateRecordException("Email_already_exist");
        User user = userMapper.toUser(req);
        try {
            userRepository.save(user);
            return userMapper.toUserDto(user);
        } catch (Exception e) {
            throw new InternalServerException("Register_user_fail");
        }
    }

    @Override
    public String login(UserReq req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        } catch (Exception e) {
            throw new InternalServerException("Login_fail");
        }
    }

}
