package com.github.fruitshop.domain.mapper;

import com.github.fruitshop.domain.dto.CategoryDto;
import com.github.fruitshop.domain.entity.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    private static final String NAME = "Joe";
    private static final long ID = 1L;

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDto() throws Exception{

        Category category = Category.builder().name(NAME).id(ID).build();

        CategoryDto categoryDto = mapper.categoryToCategoryDto(category);

        assertEquals(ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());

    }

}