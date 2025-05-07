package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.request.UserCreateDto;
import com.example.demo.domain.dto.response.UserResponseDto;
import com.example.demo.domain.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateDto userCreateDto);

    UserResponseDto toUserResponseDto(User user);

    List<UserResponseDto> toUserResponseDtos(List<User> users);
}

