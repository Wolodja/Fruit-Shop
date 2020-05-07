package com.github.fruitshop.controller.v1;

import com.github.fruitshop.domain.dto.CustomerListDto;
import com.github.fruitshop.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers(){
        return new ResponseEntity<>(
                new CustomerListDto(customerService.getAllCustomers()), HttpStatus.OK);
    }
}
