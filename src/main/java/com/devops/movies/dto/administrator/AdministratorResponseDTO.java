package com.devops.movies.dto.administrator;

import com.devops.movies.enums.Role;

public record AdministratorResponseDTO(
    Integer id,
    String login,
    Role role
) {}