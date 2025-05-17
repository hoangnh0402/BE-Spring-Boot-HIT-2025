package com.hit.passwordencoder.domain.mapper;

import com.hit.passwordencoder.domain.dto.request.UserCreateDto;
import com.hit.passwordencoder.domain.dto.response.UserDto;
import com.hit.passwordencoder.domain.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreateDto userCreateDTO);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> user);

}
