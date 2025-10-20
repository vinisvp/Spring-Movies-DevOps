package com.devops.movies.repository;

import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    
    Optional<Administrator> findByLogin(String login);
    
    boolean existsByRole(Role role);
}