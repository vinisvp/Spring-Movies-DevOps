package com.devops.movies.mapper;

import com.devops.movies.dto.GenreRequestDTO;
import com.devops.movies.dto.GenreResponseDTO;
import com.devops.movies.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    
    public Genre toEntity(GenreRequestDTO dto) {
        return new Genre(dto.name());
    }
    
    public GenreResponseDTO toResponseDTO(Genre entity) {
        return new GenreResponseDTO(entity.getId(), entity.getName());
    }
}