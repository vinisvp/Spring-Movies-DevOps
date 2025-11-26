package com.devops.movies.config;

import org.springframework.stereotype.Component;

import com.devops.movies.entity.Administrator;
import com.devops.movies.enums.Role;
import com.devops.movies.repository.AdministratorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!administratorRepository.existsByRole(Role.CEO)) {
            Administrator ceo = new Administrator();
            ceo.setLogin("ceo");
            ceo.setPassword(passwordEncoder.encode("ceo123"));
            ceo.setRole(Role.CEO);
            administratorRepository.save(ceo);

        }

    }

}
