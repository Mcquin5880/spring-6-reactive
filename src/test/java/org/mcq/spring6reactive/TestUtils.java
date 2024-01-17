package org.mcq.spring6reactive;

import org.mcq.spring6reactive.domain.Beer;
import org.mcq.spring6reactive.domain.Customer;

import java.math.BigDecimal;

public class TestUtils {

    public static Beer getTestBeer() {
        return Beer.builder()
                .name("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("123456")
                .build();
    }

    public static Customer getTestCustomer() {
        return Customer.builder()
                .name("Test Customer")
                .build();
    }
}
