package com.alten.customer.controller;

import com.alten.customer.dto.CustomerDto;
import com.alten.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;


    @Test
    public void addCustomer() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerAddress("testAddress");
        customerDto.setCustomerName("test");
        assertEquals(HttpStatus.CREATED, customerController.addCustomer(customerDto).getStatusCode());
    }

    @Test
    public void getAllCustomers() {
        assertEquals(HttpStatus.OK, customerController.getAllCustomers().getStatusCode());
    }

    @Test
    public void getCustomer() {
        assertEquals(HttpStatus.OK, customerController.getCustomer(12l).getStatusCode());
    }

    @Test
    public void deleteCustomer() {
        assertEquals(HttpStatus.NO_CONTENT, customerController.deleteCustomer(12l).getStatusCode());
    }

    @Test
    public void updateCustomer(){
        assertEquals(HttpStatus.OK,customerController.updateCustomer(12l,new CustomerDto()).getStatusCode());
    }


}
