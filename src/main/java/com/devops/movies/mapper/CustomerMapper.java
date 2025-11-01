package com.devops.movies.mapper;

import com.devops.movies.dto.CustomerRequestDTO;
import com.devops.movies.dto.CustomerResponseDTO;
import com.devops.movies.entity.Customer;
import com.devops.movies.enums.Role;
import com.devops.movies.enums.Subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class CustomerMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer toEntity(CustomerRequestDTO requestDTO) {
        return new Customer(
                requestDTO.name(),
                requestDTO.phone(),
                requestDTO.email(),
                passwordEncoder.encode(requestDTO.password()),
                Subscription.BASIC,
                Instant.now().plus(30, ChronoUnit.DAYS),
                Role.CUSTOMER);
    }

    public CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getSubscription(),
                customer.getExpiration(),
                customer.getRole());
    }

    public void updateEntity(Customer customer, CustomerRequestDTO requestDTO) {
        customer.setName(requestDTO.name());
        customer.setPhone(requestDTO.phone());
        customer.setEmail(requestDTO.email());
        customer.setPassword(passwordEncoder.encode(requestDTO.password()));
    }
}