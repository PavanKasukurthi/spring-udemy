package com.springframework.repositories;

import com.springframework.entities.Beer;
import com.springframework.entities.BeerOrder;
import com.springframework.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerOrderRepositoryTest {
    @Autowired
    BeerOrderRepository beerOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BeerRepository beerRepository;

    Customer testCustomer;
    Beer testBeer;

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.findAll().get(0);
        testBeer = beerRepository.findAll().get(0);
    }
    @Transactional
    @Test
    void testBeerOrders() {
        BeerOrder beerOrder = BeerOrder.builder()
                .customerRef("RefCustomer")
                .customer(testCustomer)
                .build();

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);

//        beerOrderRepository.flush();

        System.out.println(savedBeerOrder.getCustomerRef());
    }
}