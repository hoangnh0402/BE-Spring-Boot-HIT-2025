package com.hit.passwordencoder.service;

import com.hit.passwordencoder.domain.dto.request.UserCreateDto;
import com.hit.passwordencoder.domain.dto.response.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserCreateDto userCreateDto);
    List<UserDto> getAllUsers();
}
