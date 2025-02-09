package com.openclassrooms.chatop.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DBUserDTO {
    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "password is required")
    private String password;
}
