package com.devops.movies.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieRequestDTO(
    String title,
    String synopsis,
    Double rating,
    LocalDate release,
    List<String> posters,
    List<String> images,
    List<String> trailers,
    List<GenreIdDTO> genres,
    List<CategoryIdDTO> categories
) {
    public record GenreIdDTO(Integer id) {}
    public record CategoryIdDTO(Integer id) {}
}