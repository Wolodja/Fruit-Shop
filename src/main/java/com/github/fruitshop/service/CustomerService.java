package com.github.fruitshop.service;

import com.github.fruitshop.domain.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto findCustomerById(Long customerId);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    CustomerDto patchCustomer(Long id, CustomerDto customerDTO);

    void deleteCustomerById(Long id);
}
