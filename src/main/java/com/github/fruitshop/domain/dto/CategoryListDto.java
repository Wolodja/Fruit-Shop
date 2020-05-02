package com.github.fruitshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDto {

    private List<CategoryDto> categories;
}
