package com.devops.movies.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devops.movies.dto.AuthRequestDTO;
import com.devops.movies.dto.AuthResponseDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.entity.Customer;
import com.devops.movies.enums.Role;
import com.devops.movies.mapper.AdministratorMapper;
import com.devops.movies.mapper.AuthMapper;
import com.devops.movies.repository.AdministratorRepository;
import com.devops.movies.repository.CustomerRepository;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDTO getAuthResponse(AuthRequestDTO authRequest) {

        Optional<Administrator> admin = administratorRepository.findByLogin(authRequest.loginOrEmail());

        if (admin.isPresent()) {
            if (!passwordEncoder.matches(authRequest.password(), admin.get().getPassword())) {
                throw new RuntimeException("Senha inválida.");
            }
            return authMapper.toResponseDTO(admin.get());
        }
        Optional<Customer> customer = customerRepository.findByEmail(authRequest.loginOrEmail());

        if (customer.isPresent()) {

            if (!passwordEncoder.matches(authRequest.password(), customer.get().getPassword())) {
                throw new RuntimeException("Senha inválida.");
            }
            return authMapper.toResponseDTO(customer.get());
        }
        throw new RuntimeException("Usuário não existe.");
    }

}
