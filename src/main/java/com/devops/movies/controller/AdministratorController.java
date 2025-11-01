package com.devops.movies.controller;

import com.devops.movies.dto.AdministratorRequestDTO;
import com.devops.movies.dto.AdministratorResponseDTO;
import com.devops.movies.dto.JwtResponseDTO;
import com.devops.movies.dto.LoginDTO;
import com.devops.movies.service.AdministratorService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciamento de administradores.
 * Permite ao CEO criar, editar e excluir administradores.
 */
@RestController
@RequestMapping("/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    /**
     * Cria um novo administrador.
     * Acesso: ROLE_CEO
     */
    @PostMapping
    @PreAuthorize("hasRole('CEO')")
    public ResponseEntity<AdministratorResponseDTO> createAdministrator(
            @Valid @RequestBody AdministratorRequestDTO adminDTO) {
        AdministratorResponseDTO created = administratorService.createAdministrator(adminDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CEO')")
    public ResponseEntity<AdministratorResponseDTO> updateAdministrator(@PathVariable Integer id,
            @Valid @RequestBody AdministratorRequestDTO adminDTO) {
        AdministratorResponseDTO updated = administratorService.updateAdministrator(id, adminDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Login de administradores (ADMIN e CEO).
     * Acesso: Público
     */
    @PostMapping("/log-in")
    public ResponseEntity<JwtResponseDTO> adminLogin(@Valid @RequestBody LoginDTO loginDTO) {
        JwtResponseDTO response = administratorService.adminLogin(loginDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Lista administradores. CEO vê todos, ADMIN vê apenas própria conta.
     * Acesso: ROLE_ADMIN, ROLE_CEO
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CEO')")
    public ResponseEntity<List<AdministratorResponseDTO>> getAdministrators(Authentication authentication) {
        String login = authentication.getName();
        List<AdministratorResponseDTO> admins = administratorService.getAllAdministrators(login);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CEO')")
    public ResponseEntity<AdministratorResponseDTO> getAdministratorById(@PathVariable Integer id) {
        AdministratorResponseDTO admin = administratorService.getAdministratorById(id);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CEO')")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Integer id) {
        administratorService.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }
}