package com.devops.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.movies.dto.AuthRequestDTO;
import com.devops.movies.dto.AuthResponseDTO;
import com.devops.movies.dto.JwtResponseDTO;
import com.devops.movies.security.JwtUtils;
import com.devops.movies.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/log-in")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDTO loginRequest) {
        try {
            AuthResponseDTO response = authService.getAuthResponse(loginRequest);
            String token = jwtUtils.generateJwtToken(response.loginOrEmail(), response.role().toString());
            return ResponseEntity.ok(new JwtResponseDTO(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(java.util.Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<AuthResponseDTO> verify(@Valid @RequestBody AuthRequestDTO verifyRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            AuthResponseDTO response = authService.getAuthResponse(verifyRequest);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
