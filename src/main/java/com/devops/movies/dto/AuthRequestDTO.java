package com.devops.movies.dto;

import com.devops.movies.enums.Role;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDTO(
                @NotNull(message = "Login or Email cannot be null") String loginOrEmail,
                @NotNull(message = "Password cannot be null") String password) {

}
