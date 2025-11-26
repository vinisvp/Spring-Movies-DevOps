package com.devops.movies.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.movies.dto.UserResponseDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.entity.Customer;
import com.devops.movies.repository.AdministratorRepository;
import com.devops.movies.repository.CustomerRepository;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AdministratorRepository administratorRepository;

    public UserResponseDTO getUserByUsername(String username) {
        // Primeiro tenta encontrar como administrador
        Optional<Administrator> admin = administratorRepository.findByLogin(username);
        if (admin.isPresent()) {
            Administrator a = admin.get();
            return new UserResponseDTO(a.getId(), a.getLogin(), a.getLogin(), a.getRole().toString());
        }
        
        // Depois tenta encontrar como cliente
        Optional<Customer> customer = customerRepository.findByEmail(username);
        if (customer.isPresent()) {
            Customer c = customer.get();
            return new UserResponseDTO(c.getId(), c.getName(), c.getEmail(), c.getRole().toString());
        }
        
        throw new RuntimeException("Usuário não encontrado");
    }
}