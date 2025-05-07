package com.example.demo.service.impl;

import com.example.demo.constant.ErrorMessage;
import com.example.demo.domain.dto.response.UserResponseDto;
import com.example.demo.domain.dto.request.UserCreateDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.mapper.UserMapper;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toUserResponseDtos(userRepository.findAll());
    }

    @Override
    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{id}));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.toUser(userCreateDto);
        User saved = userRepository.save(user);
        return userMapper.toUserResponseDto(saved);
    }
}

