package com.alten.customer.repository;

import com.alten.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    void deleteByCustomerId(Long id);

}
