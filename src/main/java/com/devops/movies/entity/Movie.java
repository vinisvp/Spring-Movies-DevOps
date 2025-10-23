package com.devops.movies.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String title;
    
    private String synopsis;
    
    private Double rating;
    
    private LocalDate release;
    
    @ElementCollection
    @CollectionTable(name = "movie_posters", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "poster_url")
    private List<String> posters;
    
    @ElementCollection
    @CollectionTable(name = "movie_images", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "image_url")
    private List<String> images;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "movie_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "movie_categories",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    
    public Movie() {}
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSynopsis() {
        return synopsis;
    }
    
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public LocalDate getRelease() {
        return release;
    }
    
    public void setRelease(LocalDate release) {
        this.release = release;
    }
    
    public List<String> getPosters() {
        return posters;
    }
    
    public void setPosters(List<String> posters) {
        this.posters = posters;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public List<Genre> getGenres() {
        return genres;
    }
    
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}