package com.sesame.demo.service;

import com.sesame.demo.domain.Category;
import com.sesame.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    public Category create(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("La catégorie ne peut pas être nulle.");
        }
        return categoryRepository.save(category);
    }
}
