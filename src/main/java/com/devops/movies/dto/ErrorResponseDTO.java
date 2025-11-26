package com.devops.movies.dto;

import jakarta.validation.constraints.NotBlank;

public record ErrorResponseDTO(
        @NotBlank String error,
        String message) {
}