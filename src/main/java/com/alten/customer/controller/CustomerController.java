package com.alten.customer.controller;


import com.alten.customer.dto.CustomerDto;
import com.alten.customer.dto.CustomerResponseDto;
import com.alten.customer.mapper.CustomerMapper;
import com.alten.customer.model.Customer;
import com.alten.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CustomerController {


    private CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody CustomerDto customerDto) {

        Customer customer = CustomerMapper.INSTANCE.fromCustomerDto(customerDto);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponseDto(customerService.addCustomer(customer)), HttpStatus.CREATED);

    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerDtoList(customerService.getCustomers()), HttpStatus.OK);
    }

    @GetMapping("/customers/{customer-id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable("customer-id") Long customerId) {
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponseDto(customerService.getCustomer(customerId)), HttpStatus.OK);
    }


    @DeleteMapping("/customers/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/customers/{customer-id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("customer-id") Long customerId,
                                                      @RequestBody CustomerDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.fromCustomerDto(customerDto);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.toCustomerResponseDto(customerService.updateCustomer(customerId, customer)), HttpStatus.OK);

    }


}
