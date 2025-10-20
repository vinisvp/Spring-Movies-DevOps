package com.devops.movies.dto;

import com.devops.movies.enums.Role;

public record AdministratorResponseDTO(
    Integer id,
    String login,
    Role role
) {}