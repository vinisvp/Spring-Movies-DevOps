package com.devops.movies.service;

import com.devops.movies.dto.customer.CustomerRequestDTO;
import com.devops.movies.dto.customer.CustomerResponseDTO;
import com.devops.movies.entity.Customer;
import com.devops.movies.mapper.CustomerMapper;
import com.devops.movies.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
        if (customerRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }
        
        Customer customer = customerMapper.toEntity(requestDTO);
        Customer saved = customerRepository.save(customer);
        
        return customerMapper.toResponseDTO(saved);
    }
    
    public CustomerResponseDTO updateCustomer(Integer id, CustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        if (!customer.getEmail().equals(requestDTO.email()) && 
            customerRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }
        
        customerMapper.updateEntity(customer, requestDTO);
        Customer updated = customerRepository.save(customer);
        
        return customerMapper.toResponseDTO(updated);
    }
}