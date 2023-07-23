package com.springframework.repositories;

import com.springframework.entities.Beer;
import com.springframework.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My beersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlknclbeersandmnzxckjhnalnslkajjlkncl")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12546")
                    .price(new BigDecimal("15.99"))
                    .build());

            //Tells Hibernate to immediately write to the database
            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("12546")
                        .price(new BigDecimal("15.99"))
                .build());

        //Tells Hibernate to immediately write to the database
        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}