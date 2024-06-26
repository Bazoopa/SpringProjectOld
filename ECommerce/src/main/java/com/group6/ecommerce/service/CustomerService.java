package com.group6.ecommerce.service;

import com.group6.ecommerce.dao.CustomerRepo;
import com.group6.ecommerce.entity.Customer;
import com.group6.ecommerce.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    // Service method to get all customers
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // Service method to get a customer by its ID
    public Customer getCustomerById(Integer id) {
        Optional<Customer> maybeCustomer = customerRepo.findById(id);

        if (maybeCustomer.isPresent()) {
            return maybeCustomer.get();
        } else {
            throw new ServiceException("Customer with id " + id + " not found");
        }
    }

    // Service method to authenticate a customer by its email and password
    public Customer authenticateCustomer(String email, String password) {
        Customer customer = customerRepo.findByEmailAndPassword(email, password);
        if (customer != null) {
            return customer;
        }else {
            throw new ServiceException("Customer with email " + email + " not found");
        }
    }

    // Service method to create a new customer
    public Customer createNewCustomer(Customer customerEntity) {
        // Using email to verify if customer already exists, because an ID doesn't exist yet. - Not sure if totally needed
        if (customerRepo.existsByEmail(customerEntity.getEmail())) {
            throw new ServiceException("CustomerEntity with email " + customerEntity.getEmail() + " already exists");
        }
        return customerRepo.save(customerEntity);
    }

}