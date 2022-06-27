package com.ecommerce.shoes.controller;

import com.ecommerce.shoes.model.dto.UserDto;
import com.ecommerce.shoes.model.request.UserReq;
import com.ecommerce.shoes.service.UserService;
import com.ecommerce.shoes.util.ResponseObject;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody UserReq req) {
        UserDto result = userService.register(req);
        return ResponseEntity.ok(new ResponseObject(201, "Register_success", result));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@Valid @RequestBody UserReq req) {
        String result = userService.login(req);
        return ResponseEntity.ok(new ResponseObject(200, "Login_success", result));
    }

}
