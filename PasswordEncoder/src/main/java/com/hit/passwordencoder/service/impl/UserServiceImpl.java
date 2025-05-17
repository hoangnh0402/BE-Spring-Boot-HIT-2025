package com.hit.passwordencoder.service.impl;

import com.hit.passwordencoder.domain.dto.request.UserCreateDto;
import com.hit.passwordencoder.domain.dto.response.UserDto;
import com.hit.passwordencoder.domain.entity.User;
import com.hit.passwordencoder.domain.mapper.UserMapper;
import com.hit.passwordencoder.repository.UserRepository;
import com.hit.passwordencoder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        if (userRepository.findByUsername(userCreateDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        User user = userMapper.toUser(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toUserDtos(userRepository.findAll());
    }
}
