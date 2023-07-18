package com.springframework.service;

import com.springframework.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getCustomerList();

    Customer getCustomerById(UUID id);
}
