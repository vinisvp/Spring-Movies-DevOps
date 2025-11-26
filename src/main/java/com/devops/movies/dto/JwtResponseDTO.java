package com.devops.movies.dto;

/**
 * DTO para resposta de autenticação JWT.
 * Retorna o token de acesso após login bem-sucedido.
 */
public class JwtResponseDTO {
    private String token;
    /** Tipo do token (sempre Bearer) */
    private String type = "Bearer";

    public JwtResponseDTO(String token) {
        this.token = token;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}