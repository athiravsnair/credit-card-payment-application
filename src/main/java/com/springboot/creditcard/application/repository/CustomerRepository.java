package com.springboot.creditcard.application.repository;

import com.springboot.creditcard.application.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByUsername(String username);

    Customer findByToken(String token);
}
