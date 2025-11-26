package com.devops.movies.mapper;

import org.springframework.stereotype.Component;

import com.devops.movies.dto.AuthResponseDTO;
import com.devops.movies.entity.Administrator;
import com.devops.movies.entity.Customer;

@Component
public class AuthMapper {

    public AuthResponseDTO toResponseDTO(Administrator admin) {
        return new AuthResponseDTO(
                admin.getId(),
                admin.getLogin(),
                admin.getPassword(),
                admin.getRole());

    }

    public AuthResponseDTO toResponseDTO(Customer customer) {
        return new AuthResponseDTO(
                customer.getId(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getRole());
    }

}
