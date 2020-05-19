package com.github.fruitshop.domain.mapper;

import com.github.fruitshop.controller.v1.CustomerController;
import com.github.fruitshop.domain.entity.Customer;
import com.github.fruitshop.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    String url = CustomerController.BASE_URL;

    @Mapping(target = "customerUrl", expression = "java(url +\"/\" + customer.getId())")
    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
