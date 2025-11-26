package com.devops.movies.repository;

import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    Optional<Administrator> findByLogin(String login);

    Optional<Administrator> findByRole(Role role);

    boolean existsByRole(Role role);

    boolean existsByLogin(String login);
}