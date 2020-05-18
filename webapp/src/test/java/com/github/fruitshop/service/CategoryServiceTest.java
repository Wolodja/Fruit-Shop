package com.github.fruitshop.service;

import com.github.fruitshop.domain.dto.CategoryDto;
import com.github.fruitshop.domain.entity.Category;
import com.github.fruitshop.domain.mapper.CategoryMapper;
import com.github.fruitshop.repository.CategoryRepository;
import com.github.fruitshop.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    private static final long ID = 1L;
    private static final String NAME = "John";
    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryMapper, categoryRepository);
    }

    @Test
    public void getAllCategories() {

        List<Category> categories = Arrays.asList(Category.builder().id(ID).name(NAME).build(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> allCategories = categoryService.getAllCategories();

        assertEquals(categories.size(), allCategories.size());
    }

    @Test
    public void getCategoryByName(){
        Category category = Category.builder().name(NAME).id(ID).build();

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDto categoryDto = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, categoryDto.getName());
        assertEquals(Long.valueOf(ID), categoryDto.getId());
    }
}