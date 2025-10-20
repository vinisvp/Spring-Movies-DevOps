package com.devops.movies.entity;

import com.devops.movies.enums.Role;
import com.devops.movies.enums.Subscription;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subscription subscription;
    
    @Column(nullable = false)
    private Instant expiration;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    public Customer() {}
    
    public Customer(String name, String phone, String email, String password, Subscription subscription, Instant expiration, Role role) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.expiration = expiration;
        this.role = role;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Subscription getSubscription() {
        return subscription;
    }
    
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
    
    public Instant getExpiration() {
        return expiration;
    }
    
    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}