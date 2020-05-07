package com.github.fruitshop.domain.mapper;

import com.github.fruitshop.domain.dto.CustomerDto;
import com.github.fruitshop.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);
}
