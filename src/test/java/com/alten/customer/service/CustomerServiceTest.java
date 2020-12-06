package com.alten.customer.service;

import com.alten.customer.exception.NoCustomerFoundException;
import com.alten.customer.model.Customer;
import com.alten.customer.repository.CustomerRepository;
import com.alten.customer.service.impl.CustomerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;


    @Test
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("test");
        customer.setCustomerAddress("testAddress");
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer newCustomer = customerService.addCustomer(customer);
        assertEquals(customer.getCustomerName(), newCustomer.getCustomerName());
    }

    @Test
    public void getCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        assertEquals(customerList.size(), customerService.getCustomers().size());
    }

    @Test
    public void getCustomer() {
        Customer customer = new Customer();
        customer.setCustomerAddress("testAddress");
        customer.setCustomerName("test");
        customer.setCustomerId(12l);
        Optional<Customer> optional = Optional.of(customer);
        when(customerRepository.findById(12l)).thenReturn(optional);
        assertEquals(customerService.getCustomer(12l).getCustomerName(), customer.getCustomerName());
    }

    @Test(expected = NoCustomerFoundException.class)
    public void getCustomerException() {
        customerService.getCustomer(12l);
    }


    @Test
    public void deleteCustomer() {
        customerService.deleteCustomer(12l);
    }


    @Test
    public void updateCustomer() {

        Customer customer = new Customer();
        customer.setCustomerAddress("testAddress");
        customer.setCustomerName("test");
        customer.setCustomerId(12l);
        Optional<Customer> optional = Optional.of(customer);
        when(customerRepository.findById(12l)).thenReturn(optional);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerId(12l);
        updatedCustomer.setCustomerName("updatedCustomer");
        when(customerRepository.save(customer)).thenReturn(updatedCustomer);
        assertEquals(updatedCustomer.getCustomerId(), customerService.updateCustomer(12l, updatedCustomer).getCustomerId());
    }


    @Test(expected = NoCustomerFoundException.class)

    public void updateCustomerException(){

        customerService.updateCustomer(12l,new Customer());
    }

}
