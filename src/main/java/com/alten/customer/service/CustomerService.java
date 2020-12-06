package com.alten.customer.service;

import com.alten.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomer(long customerId);

    void deleteCustomer(long customerId);

    Customer updateCustomer(long customerId, Customer customer);

}
