package com.group6.ecommerce.controller;

import com.group6.ecommerce.entity.Customer;
import com.group6.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Endpoint to validate customer login.
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer loginDetails) {
        Customer customer = customerService.authenticateCustomer(loginDetails.getEmail(), loginDetails.getPassword());
        return ResponseEntity.ok(customer);
    }

    //Endpoint to get all customers.
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    //Endpoint to get a customer by their ID.
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        //@todo: check if possible to remove password from the return with JsonIgnore
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    //Endpoint to create a new customer.
    @PostMapping("/add")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
        customerService.createNewCustomer(customer);
        return ResponseEntity.ok(customer);
    }
}