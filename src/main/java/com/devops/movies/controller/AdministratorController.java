package com.devops.movies.controller;

import com.devops.movies.dto.AdministratorRequestDTO;
import com.devops.movies.dto.AdministratorResponseDTO;
import com.devops.movies.service.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {
    
    @Autowired
    private AdministratorService administratorService;
    
    @PostMapping
    public ResponseEntity<AdministratorResponseDTO> createAdministrator(@Valid @RequestBody AdministratorRequestDTO requestDTO) {
        try {
            AdministratorResponseDTO response = administratorService.createAdministrator(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AdministratorResponseDTO> updateAdministrator(@PathVariable Integer id, @Valid @RequestBody AdministratorRequestDTO requestDTO) {
        try {
            AdministratorResponseDTO response = administratorService.updateAdministrator(id, requestDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}