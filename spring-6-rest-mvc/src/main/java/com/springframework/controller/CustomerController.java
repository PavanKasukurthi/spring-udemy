package com.springframework.controller;

import com.springframework.model.Customer;
import com.springframework.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> listCustomers(){
        return customerService.getCustomerList();
    }

    @GetMapping("{customerId}")
    public Customer getCustomerById(@PathVariable("customerId")UUID id){
        log.debug("Get Customer By Id - in controller");
        return customerService.getCustomerById(id);
    }
}
