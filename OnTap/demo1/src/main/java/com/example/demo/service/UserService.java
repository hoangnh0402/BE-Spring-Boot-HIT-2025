package com.example.demo.service;

import com.example.demo.domain.dto.request.UserCreateDto;
import com.example.demo.domain.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    // Lấy tất cả người dùng
    List<UserResponseDto> getAllUsers();

    // Lấy người dùng theo ID
    UserResponseDto getUserById(String id);

    // Tạo người dùng mới
    UserResponseDto createUser(UserCreateDto userCreateDto);
}
