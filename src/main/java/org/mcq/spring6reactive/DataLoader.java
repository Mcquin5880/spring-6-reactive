package org.mcq.spring6reactive;

import lombok.RequiredArgsConstructor;
import org.mcq.spring6reactive.domain.Beer;
import org.mcq.spring6reactive.domain.Customer;
import org.mcq.spring6reactive.repository.BeerRepository;
import org.mcq.spring6reactive.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        loadBeerData();
        loadCustomerData();
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .name("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .name("Crank")
                        .beerStyle("Pale Ale")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .name("Sunshine City")
                        .beerStyle("IPA")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
           if (count == 0) {
               Customer cust1 = Customer.builder()
                       .name("Michael")
                       .createdDate(LocalDateTime.now())
                       .lastModifiedDate(LocalDateTime.now())
                       .build();

               Customer cust2 = Customer.builder()
                       .name("Jane")
                       .createdDate(LocalDateTime.now())
                       .lastModifiedDate(LocalDateTime.now())
                       .build();

               Customer cust3 = Customer.builder()
                       .name("Mr. Anderson")
                       .createdDate(LocalDateTime.now())
                       .lastModifiedDate(LocalDateTime.now())
                       .build();

               customerRepository.save(cust1).subscribe();
               customerRepository.save(cust2).subscribe();
               customerRepository.save(cust3).subscribe();
           }
        });
    }
}
