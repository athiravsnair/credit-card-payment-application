package com.springboot.creditcard.application.controller;

import com.springboot.creditcard.application.model.Customer;
import com.springboot.creditcard.application.request.LoginRequest;
import com.springboot.creditcard.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CustomerController(CustomerService customerService, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerUser(@RequestBody Customer customer) {
        Customer newCustomer = customerService.registerCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = UUID.randomUUID().toString();
            customerService.saveToken(loginRequest.getUsername(), token);
            return ResponseEntity.ok().header("token", token).body("Login successful!");
        } else
            return ResponseEntity.badRequest().body("Invalid credentials!");

    }


}
