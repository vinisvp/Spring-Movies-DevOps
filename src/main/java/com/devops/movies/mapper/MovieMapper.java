package com.devops.movies.mapper;

import com.devops.movies.dto.movie.MovieRequestDTO;
import com.devops.movies.dto.movie.MovieResponseDTO;
import com.devops.movies.entity.Category;
import com.devops.movies.entity.Genre;
import com.devops.movies.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    
    public Movie toEntity(MovieRequestDTO dto, List<Genre> genres, List<Category> categories) {
        Movie movie = new Movie();
        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setRating(dto.rating());
        movie.setRelease(dto.release());
        movie.setPosters(dto.posters());
        movie.setImages(dto.images());
        movie.setTrailers(dto.trailers());
        movie.setGenres(genres);
        movie.setCategories(categories);
        return movie;
    }
    
    public void updateEntity(Movie movie, MovieRequestDTO dto, List<Genre> genres, List<Category> categories) {
        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setRating(dto.rating());
        movie.setRelease(dto.release());
        movie.setPosters(dto.posters());
        movie.setImages(dto.images());
        movie.setTrailers(dto.trailers());
        movie.setGenres(genres);
        movie.setCategories(categories);
    }
    
    public MovieResponseDTO toResponseDTO(Movie movie) {
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
            movie.getTrailers(),
            genreDTOs,
            categoryDTOs
        );
    }
}