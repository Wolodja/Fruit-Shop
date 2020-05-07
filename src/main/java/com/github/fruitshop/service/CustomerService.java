package com.github.fruitshop.service;

import com.github.fruitshop.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto findCustomerById(Long customerId);
}
