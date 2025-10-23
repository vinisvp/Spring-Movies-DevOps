package com.devops.movies.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieResponseDTO(
    Integer id,
    String title,
    String synopsis,
    Double rating,
    LocalDate release,
    List<String> posters,
    List<String> images,
    List<String> trailers,
    List<GenreDTO> genres,
    List<CategoryDTO> categories
) {
    public record GenreDTO(Integer id, String name) {}
    public record CategoryDTO(Integer id, String name) {}
}