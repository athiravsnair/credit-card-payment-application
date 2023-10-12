package com.springboot.creditcard.application.service;

import com.springboot.creditcard.application.model.Role;
import com.springboot.creditcard.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.springboot.creditcard.application.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManagementService implements UserDetailsService {


    private CustomerRepository customerRepository;

    public UserManagementService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found:" + username);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : customer.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(),
                grantedAuthorities);
    }
}
