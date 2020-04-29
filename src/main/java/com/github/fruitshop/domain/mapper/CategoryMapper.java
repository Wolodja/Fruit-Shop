package com.github.fruitshop.domain.mapper;

import com.github.fruitshop.domain.dto.CategoryDto;
import com.github.fruitshop.domain.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);
}
