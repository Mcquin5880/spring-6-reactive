package org.mcq.spring6reactive.repository;

import org.junit.jupiter.api.Test;
import org.mcq.spring6reactive.config.DatabaseConfig;
import org.mcq.spring6reactive.domain.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveNewBeer() {
        beerRepository.save(getTestBeer())
                .subscribe(System.out::println);
    }

    Beer getTestBeer() {
        return Beer.builder()
                .name("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("123456")
                .build();
    }
}
