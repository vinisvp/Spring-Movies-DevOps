package com.devops.movies.service;

import com.devops.movies.dto.CategoryRequestDTO;
import com.devops.movies.dto.CategoryResponseDTO;
import com.devops.movies.entity.Category;
import com.devops.movies.mapper.CategoryMapper;
import com.devops.movies.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        Category category = categoryMapper.toEntity(requestDTO);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(saved);
    }
    
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        categoryMapper.updateEntity(category, requestDTO);
        Category updated = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(updated);
    }
    
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponseDTOList(categories);
    }
    
    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return categoryMapper.toResponseDTO(category);
    }
    
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoryRepository.deleteById(id);
    }
}