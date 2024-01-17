package org.mcq.spring6reactive.controller;

import lombok.RequiredArgsConstructor;
import org.mcq.spring6reactive.model.CustomerDTO;
import org.mcq.spring6reactive.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v2/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{id}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    Flux<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    Mono<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(CUSTOMER_PATH)
    Mono<ResponseEntity<Void>> createNewCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.saveNewCustomer(customerDTO)
                .map(savedDto -> ResponseEntity.created(UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/" + CUSTOMER_PATH + "/" +  savedDto.getId())
                        .build().toUri())
                        .build());
    }

    @PutMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> updateExistingCustomer(@PathVariable Integer id,
                                                      @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(savedDto -> ResponseEntity.noContent().build());
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> patchExistingCustomer(@PathVariable Integer id,
                                                     @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(updatedDto -> ResponseEntity.noContent().build());
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable Integer id) {

        return customerService.getCustomerById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(customerDTO -> customerService.deleteCustomer(customerDTO.getId()))
                .thenReturn(ResponseEntity.noContent().build());
    }
}
