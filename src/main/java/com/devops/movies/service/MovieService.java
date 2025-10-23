package com.devops.movies.service;

import com.devops.movies.dto.MovieRequestDTO;
import com.devops.movies.dto.MovieResponseDTO;
import com.devops.movies.entity.Category;
import com.devops.movies.entity.Genre;
import com.devops.movies.entity.Movie;
import com.devops.movies.mapper.MovieMapper;
import com.devops.movies.repository.CategoryRepository;
import com.devops.movies.repository.GenreRepository;
import com.devops.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private MovieMapper movieMapper;
    
    public MovieResponseDTO create(MovieRequestDTO dto) {
        List<Genre> genres = dto.genres().stream()
            .map(g -> genreRepository.findById(g.id()).orElseThrow())
            .collect(Collectors.toList());
        
        List<Category> categories = dto.categories().stream()
            .map(c -> categoryRepository.findById(c.id()).orElseThrow())
            .collect(Collectors.toList());
        
        Movie movie = movieMapper.toEntity(dto, genres, categories);
        Movie saved = movieRepository.save(movie);
        return movieMapper.toResponseDTO(saved);
    }
    
    public MovieResponseDTO update(Integer id, MovieRequestDTO dto) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        
        List<Genre> genres = dto.genres().stream()
            .map(g -> genreRepository.findById(g.id()).orElseThrow())
            .collect(Collectors.toList());
        
        List<Category> categories = dto.categories().stream()
            .map(c -> categoryRepository.findById(c.id()).orElseThrow())
            .collect(Collectors.toList());
        
        movieMapper.updateEntity(movie, dto, genres, categories);
        Movie updated = movieRepository.save(movie);
        return movieMapper.toResponseDTO(updated);
    }
    
    public List<MovieResponseDTO> findAll(String search, String filter) {
        List<Movie> movies;
        
        if (search != null && !search.isEmpty()) {
            movies = movieRepository.findByTitleContainingIgnoreCase(search);
        } else if (filter != null && !filter.isEmpty()) {
            movies = movieRepository.findByGenreOrCategoryName(filter);
        } else {
            movies = movieRepository.findAll();
        }
        
        return movies.stream()
            .map(movieMapper::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    public MovieResponseDTO findById(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return movieMapper.toResponseDTO(movie);
    }
}