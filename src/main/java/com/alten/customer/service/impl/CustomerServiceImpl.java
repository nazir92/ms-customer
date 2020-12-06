package com.alten.customer.service.impl;

import com.alten.customer.exception.NoCustomerFoundException;
import com.alten.customer.model.Customer;
import com.alten.customer.repository.CustomerRepository;
import com.alten.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        LOGGER.info("Add new customer request received! {}", customer);

        Customer newCustomer = customerRepository.save(customer);
        LOGGER.info("Customer saved successfully! {}", newCustomer);
        return newCustomer;
    }

    @Override
    public List<Customer> getCustomers() {
        LOGGER.info("Get all customers request received!");
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(long customerId) {
        LOGGER.info("Get customer request received! CustomerId: {}", customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            LOGGER.error("Couldn't find requested customer! customerId: {}", customerId);
            throw new NoCustomerFoundException("Couldn't find requested customer! customerId: " + customerId);
        }
        return optionalCustomer.get();
    }

    @Override
    @Transactional
    public void deleteCustomer(long customerId) {
        LOGGER.info("Delete customer request received! customerId: {}", customerId);
        customerRepository.deleteByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Customer updateCustomer(long customerId, Customer customer) {
        LOGGER.info("Update customer request received! customerId: ", customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (!optionalCustomer.isPresent()) {
            LOGGER.error("Couldn't find customer for update! customerId: {}", customerId);
            throw new NoCustomerFoundException("Couldn't find customer for update! customerId: " + customerId);
        }

        Customer legacyCustomer = optionalCustomer.get();

        legacyCustomer.setCustomerAddress(customer.getCustomerAddress());
        legacyCustomer.setCustomerName(customer.getCustomerName());


        return customerRepository.save(legacyCustomer);
    }
}
