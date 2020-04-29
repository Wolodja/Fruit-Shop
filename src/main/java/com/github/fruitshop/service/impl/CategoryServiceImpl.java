package com.github.fruitshop.service.impl;

import com.github.fruitshop.domain.dto.CategoryDto;
import com.github.fruitshop.domain.mapper.CategoryMapper;
import com.github.fruitshop.repository.CategoryRepository;
import com.github.fruitshop.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findByName(name));
    }
}
