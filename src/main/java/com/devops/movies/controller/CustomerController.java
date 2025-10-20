package com.devops.movies.controller;

import com.devops.movies.dto.CustomerRequestDTO;
import com.devops.movies.dto.CustomerResponseDTO;
import com.devops.movies.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO response = customerService.createCustomer(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO response = customerService.updateCustomer(id, requestDTO);
        return ResponseEntity.ok(response);
    }
}