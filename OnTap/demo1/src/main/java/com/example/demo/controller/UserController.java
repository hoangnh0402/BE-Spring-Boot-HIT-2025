package com.example.demo.controller;

import com.example.demo.base.RestApiV1;
import com.example.demo.base.VsResponseUtil;
import com.example.demo.constant.UrlConstant;
import com.example.demo.domain.dto.request.UserCreateDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestApiV1
public class UserController {

    private final UserService userService;

    @GetMapping(UrlConstant.User.GET_USERS)
    public ResponseEntity<?> getAllUsers() {
        return VsResponseUtil.success(userService.getAllUsers());
    }

    @GetMapping(UrlConstant.User.GET_USER)
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        return VsResponseUtil.success(userService.getUserById(userId));
    }

    @PostMapping(UrlConstant.User.CREATE_USER)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return VsResponseUtil.success(userService.createUser(userCreateDto));
    }
}
