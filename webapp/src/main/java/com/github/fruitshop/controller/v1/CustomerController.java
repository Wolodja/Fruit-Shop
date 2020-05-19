package com.github.fruitshop.controller.v1;

import com.github.fruitshop.model.CustomerDto;
import com.github.fruitshop.model.CustomerListDto;
import com.github.fruitshop.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Customer API")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get a list of customers", notes = "Some notes.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getAllCustomers() {
        CustomerListDto customerListDto = new CustomerListDto();
        customerListDto.getCustomers().addAll(customerService.getAllCustomers());
        return customerListDto;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto) {
        System.out.print(customerDto.toString());
        return customerService.createNewCustomer(customerDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.patchCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}