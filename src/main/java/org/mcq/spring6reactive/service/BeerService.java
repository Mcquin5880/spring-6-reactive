package org.mcq.spring6reactive.service;

import org.mcq.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {

    Flux<BeerDTO> listBeers(String name, String beerStyle);

    Mono<BeerDTO> getBeerById(Integer id);

    Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(Integer id, BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(Integer id, BeerDTO beerDTO);

    Mono<Void> deleteBeerById(Integer id);
}
