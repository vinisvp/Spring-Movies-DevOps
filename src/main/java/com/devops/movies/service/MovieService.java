package com.devops.movies.service;

import com.devops.movies.dto.MovieRequestDTO;
import com.devops.movies.dto.MovieResponseDTO;
import com.devops.movies.entity.Category;
import com.devops.movies.entity.Genre;
import com.devops.movies.entity.Movie;
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
    
    public MovieResponseDTO create(MovieRequestDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setRating(dto.rating());
        movie.setRelease(dto.release());
        movie.setPosters(dto.posters());
        movie.setImages(dto.images());
        
        List<Genre> genres = dto.genres().stream()
            .map(g -> genreRepository.findById(g.id()).orElseThrow())
            .collect(Collectors.toList());
        movie.setGenres(genres);
        
        List<Category> categories = dto.categories().stream()
            .map(c -> categoryRepository.findById(c.id()).orElseThrow())
            .collect(Collectors.toList());
        movie.setCategories(categories);
        
        Movie saved = movieRepository.save(movie);
        return toResponseDTO(saved);
    }
    
    public MovieResponseDTO update(Integer id, MovieRequestDTO dto) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setRating(dto.rating());
        movie.setRelease(dto.release());
        movie.setPosters(dto.posters());
        movie.setImages(dto.images());
        
        List<Genre> genres = dto.genres().stream()
            .map(g -> genreRepository.findById(g.id()).orElseThrow())
            .collect(Collectors.toList());
        movie.setGenres(genres);
        
        List<Category> categories = dto.categories().stream()
            .map(c -> categoryRepository.findById(c.id()).orElseThrow())
            .collect(Collectors.toList());
        movie.setCategories(categories);
        
        Movie updated = movieRepository.save(movie);
        return toResponseDTO(updated);
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
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }
    
    public MovieResponseDTO findById(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return toResponseDTO(movie);
    }
    
    private MovieResponseDTO toResponseDTO(Movie movie) {
        List<MovieResponseDTO.GenreDTO> genreDTOs = movie.getGenres().stream()
            .map(g -> new MovieResponseDTO.GenreDTO(g.getId(), g.getName()))
            .collect(Collectors.toList());
        
        List<MovieResponseDTO.CategoryDTO> categoryDTOs = movie.getCategories().stream()
            .map(c -> new MovieResponseDTO.CategoryDTO(c.getId(), c.getName()))
            .collect(Collectors.toList());
        
        return new MovieResponseDTO(
            movie.getId(),
            movie.getTitle(),
            movie.getSynopsis(),
            movie.getRating(),
            movie.getRelease(),
            movie.getPosters(),
            movie.getImages(),
            genreDTOs,
            categoryDTOs
        );
    }
}