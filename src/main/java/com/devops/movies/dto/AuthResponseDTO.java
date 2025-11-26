package com.devops.movies.dto;

import com.devops.movies.enums.Role;

public record AuthResponseDTO(
                Integer id,
                String loginOrEmail,
                String password,
                Role role) {
}