package org.mcq.spring6reactive.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mcq.spring6reactive.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mcq.spring6reactive.TestUtils.getTestCustomer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(2)
    void testListCustomers() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(1)
    void testGetById() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody(CustomerDTO.class);
    }

    @Test
    void testCreateCustomer() {
        webTestClient.post().uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(getTestCustomer()), CustomerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/customer/4");
    }

    @Test
    @Order(3)
    void testUpdateCustomer() {
        webTestClient.put()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(4)
    void testDeleteCustomer() {
        webTestClient.delete()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}