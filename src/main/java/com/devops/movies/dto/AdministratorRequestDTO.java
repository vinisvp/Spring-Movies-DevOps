package com.devops.movies.dto;

import jakarta.validation.constraints.NotBlank;

public record AdministratorRequestDTO(
    @NotBlank(message = "Login é obrigatório")
    String login,
    
    @NotBlank(message = "Password é obrigatório")
    String password
) {}