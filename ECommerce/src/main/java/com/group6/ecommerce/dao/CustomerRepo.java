package com.group6.ecommerce.dao;

import com.group6.ecommerce.entity.Customer;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

// extending JpaRepository for our database operations
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByEmailAndPassword(String email, String password);

    // check for the existence of a customer by email
    boolean existsByEmail(@NotNull String email);
}
