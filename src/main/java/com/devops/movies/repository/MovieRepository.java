package com.devops.movies.repository;

import com.devops.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    
    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Movie> findByTitleContainingIgnoreCase(@Param("title") String title);
    
    @Query("SELECT DISTINCT m FROM Movie m LEFT JOIN m.genres g LEFT JOIN m.categories c WHERE LOWER(g.name) = LOWER(:tag) OR LOWER(c.name) = LOWER(:tag)")
    List<Movie> findByGenreOrCategoryName(@Param("tag") String tag);
}