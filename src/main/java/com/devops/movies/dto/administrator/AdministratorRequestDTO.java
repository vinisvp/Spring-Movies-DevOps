package com.devops.movies.dto.administrator;

import jakarta.validation.constraints.NotBlank;

public record AdministratorRequestDTO(
    @NotBlank(message = "Login é obrigatório")
    String login,
    
    @NotBlank(message = "Password é obrigatório")
    String password
) {}