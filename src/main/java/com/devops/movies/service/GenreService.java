package com.devops.movies.service;

import com.devops.movies.dto.genre.GenreRequestDTO;
import com.devops.movies.dto.genre.GenreResponseDTO;
import com.devops.movies.entity.Genre;
import com.devops.movies.mapper.GenreMapper;
import com.devops.movies.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private GenreMapper genreMapper;
    
    public GenreResponseDTO create(GenreRequestDTO dto) {
        Genre genre = genreMapper.toEntity(dto);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.toResponseDTO(savedGenre);
    }
    
    public GenreResponseDTO update(Integer id, GenreRequestDTO dto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        genre.setName(dto.name());
        Genre updatedGenre = genreRepository.save(genre);
        return genreMapper.toResponseDTO(updatedGenre);
    }
    
    public List<GenreResponseDTO> findAll() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public GenreResponseDTO findById(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        return genreMapper.toResponseDTO(genre);
    }
    
    public void delete(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Genre not found");
        }
        genreRepository.deleteById(id);
    }
}