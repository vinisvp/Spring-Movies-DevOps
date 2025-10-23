package com.devops.movies.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    String name
) {}