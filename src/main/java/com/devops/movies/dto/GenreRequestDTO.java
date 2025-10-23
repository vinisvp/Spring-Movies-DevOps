package com.devops.movies.dto;

public class GenreRequestDTO {
    
    private String name;
    
    public GenreRequestDTO() {}
    
    public GenreRequestDTO(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}