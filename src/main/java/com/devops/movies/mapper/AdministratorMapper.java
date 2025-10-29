package com.devops.movies.mapper;

import com.devops.movies.dto.administrator.AdministratorRequestDTO;
import com.devops.movies.dto.administrator.AdministratorResponseDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class AdministratorMapper {
    
    public Administrator toEntity(AdministratorRequestDTO dto, Role role) {
        return new Administrator(dto.login(), dto.password(), role);
    }
    
    public AdministratorResponseDTO toResponseDTO(Administrator entity) {
        return new AdministratorResponseDTO(entity.getId(), entity.getLogin(), entity.getRole());
    }
    
    public void updateEntity(Administrator entity, AdministratorRequestDTO dto) {
        entity.setLogin(dto.login());
        entity.setPassword(dto.password());
    }
}