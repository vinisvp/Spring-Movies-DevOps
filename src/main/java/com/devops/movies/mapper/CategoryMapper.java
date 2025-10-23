package com.devops.movies.mapper;

import com.devops.movies.dto.category.CategoryRequestDTO;
import com.devops.movies.dto.category.CategoryResponseDTO;
import com.devops.movies.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    
    public Category toEntity(CategoryRequestDTO dto) {
        return new Category(dto.name());
    }
    
    public CategoryResponseDTO toResponseDTO(Category entity) {
        return new CategoryResponseDTO(entity.getId(), entity.getName());
    }
    
    public List<CategoryResponseDTO> toResponseDTOList(List<Category> entities) {
        return entities.stream().map(this::toResponseDTO).toList();
    }
    
    public void updateEntity(Category entity, CategoryRequestDTO dto) {
        entity.setName(dto.name());
    }
}