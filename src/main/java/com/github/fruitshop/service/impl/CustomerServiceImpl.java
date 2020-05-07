package com.github.fruitshop.service.impl;

import com.github.fruitshop.domain.dto.CustomerDto;
import com.github.fruitshop.domain.mapper.CustomerMapper;
import com.github.fruitshop.repository.CustomerRepository;
import com.github.fruitshop.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long customerId) {
        return customerRepository
                .findById(customerId)
                .map(customerMapper::customerToCustomerDto)
                .orElse(null);
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        return customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customerDto)));
    }
}
