package com.devops.movies.dto;

import com.devops.movies.enums.Role;
import com.devops.movies.enums.Subscription;
import java.time.Instant;

public record CustomerResponseDTO(
    Integer id,
    String name,
    String phone,
    String email,
    Subscription subscription,
    Instant expiration,
    Role role
) {}