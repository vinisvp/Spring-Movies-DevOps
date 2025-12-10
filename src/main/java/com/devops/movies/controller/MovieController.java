package com.devops.movies.controller;

import com.devops.movies.dto.movie.MovieRequestDTO;
import com.devops.movies.dto.movie.MovieResponseDTO;
import com.devops.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@RequestBody MovieRequestDTO dto) {
        MovieResponseDTO response = movieService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable Integer id, @RequestBody MovieRequestDTO dto) {
        MovieResponseDTO response = movieService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String filter) {
        List<MovieResponseDTO> response = movieService.findAll(search, filter);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable Integer id) {
        MovieResponseDTO response = movieService.findById(id);
        return ResponseEntity.ok(response);
    }
}