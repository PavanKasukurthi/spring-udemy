package com.springframework.controller;

import com.springframework.entities.Customer;
import com.springframework.mappers.CustomerMapper;
import com.springframework.model.CustomerDTO;
import com.springframework.repositories.BeerRepository;
import com.springframework.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.CHAR_2D_ARRAY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CustomerControllerIT {
    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testCustomerList() {
        List<CustomerDTO> customerDTOS =  customerController.listCustomers();

        assertThat(customerDTOS.size()).isEqualTo(3);
    }
    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        customerRepository.deleteAll();
        List<CustomerDTO> customerDTOS = customerController.listCustomers();

        assertThat(customerDTOS.size()).isEqualTo(0);
    }

    @Test
    void testCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO).isNotNull();
    }

    @Test
    void testCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
          customerController.getCustomerById(UUID.randomUUID());
        });
    }
    @Rollback
    @Transactional
    @Test
    void testSaveNewCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("New Customer")
                .build();

        ResponseEntity responseEntity = customerController.handlePost(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();

        assertThat(customer).isNotNull();
    }
    @Rollback
    @Transactional
    @Test
    void updateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);

        final String customerName = "New Customer";
        customerDTO.setName(customerName);

        ResponseEntity responseEntity = customerController.updateById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();

        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getName()).isEqualTo(customerName);
    }

    @Test
    void updateExistingCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateById(UUID.randomUUID(), CustomerDTO.builder().build());
        });
    }
    @Rollback
    @Transactional
    @Test
    void testDeleteCustomer() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        ResponseEntity responseEntity = customerController.deleteById(customer.getId());

        assertThat(customerRepository.findById(customer.getId())).isEmpty();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
    }

    @Test
    void testDeleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteById(UUID.randomUUID());
        });
    }
}