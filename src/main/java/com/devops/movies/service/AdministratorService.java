package com.devops.movies.service;

import com.devops.movies.dto.AdministratorRequestDTO;
import com.devops.movies.dto.AdministratorResponseDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import com.devops.movies.mapper.AdministratorMapper;
import com.devops.movies.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    
    @Autowired
    private AdministratorRepository administratorRepository;
    
    @Autowired
    private AdministratorMapper administratorMapper;
    
    public AdministratorResponseDTO createAdministrator(AdministratorRequestDTO requestDTO) {
        if (administratorRepository.findByLogin(requestDTO.login()).isPresent()) {
            throw new RuntimeException("Login já existe");
        }
        
        Administrator administrator = administratorMapper.toEntity(requestDTO, Role.ADMINISTRATOR);
        Administrator saved = administratorRepository.save(administrator);
        
        return administratorMapper.toResponseDTO(saved);
    }
    
    public AdministratorResponseDTO updateAdministrator(Integer id, AdministratorRequestDTO requestDTO) {
        Administrator administrator = administratorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        
        if (!administrator.getLogin().equals(requestDTO.login()) && 
            administratorRepository.findByLogin(requestDTO.login()).isPresent()) {
            throw new RuntimeException("Login já existe");
        }
        
        administratorMapper.updateEntity(administrator, requestDTO);
        Administrator updated = administratorRepository.save(administrator);
        
        return administratorMapper.toResponseDTO(updated);
    }
}