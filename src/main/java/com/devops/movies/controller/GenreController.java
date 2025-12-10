package com.devops.movies.controller;

import com.devops.movies.dto.genre.GenreRequestDTO;
import com.devops.movies.dto.genre.GenreResponseDTO;
import com.devops.movies.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponseDTO> create(@RequestBody GenreRequestDTO dto) {
        GenreResponseDTO response = genreService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> update(@PathVariable Integer id, @RequestBody GenreRequestDTO dto) {
        GenreResponseDTO response = genreService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> findAll() {
        List<GenreResponseDTO> response = genreService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> findById(@PathVariable Integer id) {
        GenreResponseDTO response = genreService.findById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}