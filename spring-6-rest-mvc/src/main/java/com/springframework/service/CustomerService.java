package com.springframework.service;

import com.springframework.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> getCustomerList();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, CustomerDTO customer);
}
