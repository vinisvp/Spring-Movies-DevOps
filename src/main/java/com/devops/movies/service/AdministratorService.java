package com.devops.movies.service;

import com.devops.movies.dto.administrator.AdministratorRequestDTO;
import com.devops.movies.dto.administrator.AdministratorResponseDTO;
import com.devops.movies.dto.JwtResponseDTO;
import com.devops.movies.dto.LoginDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import com.devops.movies.repository.AdministratorRepository;
import com.devops.movies.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para gerenciamento de administradores.
 * Controla criação, edição e autenticação de admins e CEO.
 */
@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Cria novo administrador com role ADMIN.
     * Apenas CEO pode criar administradores.
     */
    public AdministratorResponseDTO createAdministrator(AdministratorRequestDTO adminDTO) {
        if (administratorRepository.existsByLogin(adminDTO.login())) {
            throw new RuntimeException("Login já cadastrado");
        }

        Administrator admin = new Administrator();
        admin.setLogin(adminDTO.login());
        admin.setPassword(passwordEncoder.encode(adminDTO.password()));
        admin.setRole(Role.ADMINISTRATOR);

        admin = administratorRepository.save(admin);
        return convertToDTO(admin);
    }

    public AdministratorResponseDTO updateAdministrator(Integer id, AdministratorRequestDTO adminDTO) {
        Administrator admin = administratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        if (!admin.getLogin().equals(adminDTO.login())) {
            if (administratorRepository.existsByLogin(adminDTO.login())) {
                throw new RuntimeException("Login já cadastrado");
            }
            admin.setLogin(adminDTO.login());
        }

        if (adminDTO.password() != null && !adminDTO.password().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(adminDTO.password()));
        }

        admin = administratorRepository.save(admin);
        return convertToDTO(admin);
    }

    public JwtResponseDTO adminLogin(LoginDTO loginDTO) {
        Administrator admin = administratorRepository.findByLogin(loginDTO.getLogin())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String role = admin.getRole() == Role.CEO ? "ROLE_CEO" : "ROLE_ADMIN";
        String token = jwtUtils.generateJwtToken(admin.getLogin(), role);
        return new JwtResponseDTO(token);
    }

    /**
     * Lista administradores baseado no role.
     * CEO vê todos, ADMIN vê apenas própria conta.
     */
    public List<AdministratorResponseDTO> getAllAdministrators(String currentLogin) {
        Administrator currentAdmin = administratorRepository.findByLogin(currentLogin)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        if (currentAdmin.getRole() == Role.CEO) {
            return administratorRepository.findAll().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return List.of(convertToDTO(currentAdmin));
        }
    }

    public AdministratorResponseDTO getAdministratorById(Integer id) {
        Administrator admin = administratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        return convertToDTO(admin);
    }

    public void deleteAdministrator(Integer id) {
        Administrator admin = administratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        if (admin.getRole() == Role.CEO) {
            throw new RuntimeException("Não é possível excluir o CEO");
        }

        administratorRepository.delete(admin);
    }

    public AdministratorResponseDTO updateCEO(String login, AdministratorRequestDTO adminDTO) {
        Administrator ceo = administratorRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("CEO não encontrado"));

        if (!ceo.getLogin().equals(adminDTO.login())) {
            if (administratorRepository.existsByLogin(adminDTO.login())) {
                throw new RuntimeException("Login já cadastrado");
            }
            ceo.setLogin(adminDTO.login());
        }

        if (adminDTO.password() != null && !adminDTO.password().isEmpty()) {
            ceo.setPassword(passwordEncoder.encode(adminDTO.password()));
        }

        ceo = administratorRepository.save(ceo);
        return convertToDTO(ceo);
    }

    public AdministratorResponseDTO getCEO(String login) {
        Administrator ceo = administratorRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("CEO não encontrado"));
        return convertToDTO(ceo);
    }

    private AdministratorResponseDTO convertToDTO(Administrator admin) {
        AdministratorResponseDTO dto = new AdministratorResponseDTO(
                admin.getId(),
                admin.getLogin(),
                admin.getRole());

        return dto;
    }
}