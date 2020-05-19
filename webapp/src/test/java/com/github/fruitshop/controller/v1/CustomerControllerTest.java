package com.github.fruitshop.controller.v1;

import com.github.fruitshop.domain.exception.ResourceNotFoundException;
import com.github.fruitshop.model.CustomerDto;
import com.github.fruitshop.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest{

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }


    @Test
    private void testGetAllCustomers() throws Exception {
        CustomerDto john = new CustomerDto();
        john.setFirstName("John");
        john.setLastName("Smith");

        CustomerDto longi = new CustomerDto();
        john.setFirstName("Longi");
        john.setLastName("Ardo");


        List<CustomerDto> customers = Arrays.asList(john, longi);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CategoryController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        //given
        CustomerDto customer1 = new CustomerDto();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customer1.setCustomerUrl("/api/v1/customer/1");
        when(customerService.findCustomerById(anyLong())).thenReturn(customer1);
        //when
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Michale")));
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDto customer = new CustomerDto();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        CustomerDto returnDTO = new CustomerDto();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createNewCustomer(any(CustomerDto.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDto customer = new CustomerDto();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        CustomerDto returnDTO = new CustomerDto();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.updateCustomer(anyLong(), any(CustomerDto.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {

        //given
        CustomerDto customer = new CustomerDto();
        customer.setFirstName("Fred");

        CustomerDto returnDto = new CustomerDto();
        returnDto.setFirstName(customer.getFirstName());
        returnDto.setLastName("Flintstone");
        returnDto.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDto.class))).thenReturn(returnDto);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(customerService.findCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}