package com.devops.movies.dto;

import jakarta.validation.constraints.NotBlank;

public class AdministratorRequestDTO {
    
    @NotBlank(message = "Login é obrigatório")
    private String login;
    
    @NotBlank(message = "Password é obrigatório")
    private String password;
    
    public AdministratorRequestDTO() {}
    
    public AdministratorRequestDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}