package org.mcq.spring6reactive.repository;

import org.mcq.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {

    Flux<Beer> findByName(String name);

    Flux<Beer> findByBeerStyle(String beerStyle);
}
