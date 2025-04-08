package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
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
    void testBeerNameTooLong() {

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer adjkznlcxnlakjlkjoasjdonzxlcknalnlfkjsakladjkznlcxnlakjlkjoasjdonzxlcknalnlfkjsakladjkznlcxnlakjlkjoasjdonzxlcknalnlfkjsakl")
                    .beerStyle(BeerStyle.LAGER)
                    .upc("6546213")
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.flush(); //Tells Hibernate to immediately write to the DB
        });
    }

    @Test
    void testSaveBeer() {
        Beer savedBeer = beerRepository.save(Beer.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyle.LAGER)
                .upc("6546213")
                .price(new BigDecimal("11.99"))
                .build());

        beerRepository.flush(); //Tells Hibernate to immediately write to the DB

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}