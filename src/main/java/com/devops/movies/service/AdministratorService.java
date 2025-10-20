package com.devops.movies.service;

import com.devops.movies.dto.AdministratorRequestDTO;
import com.devops.movies.dto.AdministratorResponseDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import com.devops.movies.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    
    @Autowired
    private AdministratorRepository administratorRepository;
    
    public AdministratorResponseDTO createAdministrator(AdministratorRequestDTO requestDTO) {
        if (administratorRepository.findByLogin(requestDTO.getLogin()).isPresent()) {
            throw new RuntimeException("Login já existe");
        }
        
        Role role = administratorRepository.existsByRole(Role.CEO) ? Role.ADMINISTRATOR : Role.CEO;
        
        Administrator administrator = new Administrator(
            requestDTO.getLogin(),
            requestDTO.getPassword(),
            role
        );
        
        Administrator saved = administratorRepository.save(administrator);
        return new AdministratorResponseDTO(saved);
    }
    
    public AdministratorResponseDTO updateAdministrator(Integer id, AdministratorRequestDTO requestDTO) {
        Administrator administrator = administratorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        
        if (!administrator.getLogin().equals(requestDTO.getLogin()) && 
            administratorRepository.findByLogin(requestDTO.getLogin()).isPresent()) {
            throw new RuntimeException("Login já existe");
        }
        
        administrator.setLogin(requestDTO.getLogin());
        administrator.setPassword(requestDTO.getPassword());
        
        Administrator updated = administratorRepository.save(administrator);
        return new AdministratorResponseDTO(updated);
    }
}