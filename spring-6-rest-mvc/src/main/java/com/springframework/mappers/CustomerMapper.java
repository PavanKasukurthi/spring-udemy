package com.springframework.mappers;

import com.springframework.entities.Customer;
import com.springframework.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO  customerToCustomerDto(Customer customer);

    Customer customerDtoTocustomer(CustomerDTO customerDTO);
}
