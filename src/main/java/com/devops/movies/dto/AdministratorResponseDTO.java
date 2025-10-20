package com.devops.movies.dto;

import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;

public class AdministratorResponseDTO {
    
    private Integer id;
    private String login;
    private Role role;
    
    public AdministratorResponseDTO() {}
    
    public AdministratorResponseDTO(Administrator administrator) {
        this.id = administrator.getId();
        this.login = administrator.getLogin();
        this.role = administrator.getRole();
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}