package com.example.demo.domain.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.demo.constant.ErrorMessage;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateDto {

    @NotNull(message = ErrorMessage.User.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String username;

    @NotNull(message = ErrorMessage.User.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$", message = ErrorMessage.User.UINVALID_FORMAT_PASSWORD)
    private String password;

    @NotBlank(message = ErrorMessage.User.NOT_BLANK_FIELD)
    private String firstName;

    @NotBlank(message = ErrorMessage.User.NOT_BLANK_FIELD)
    private String lastName;

}
