package com.github.fruitshop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String name;
}