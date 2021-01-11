package com.example.wp_reactive_test;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//http://jsonpath.herokuapp.com/
public class OrderControllerTest {

    @Test
    public void shouldReturnRecentOrder() {
        Order[] orders = {
                testOrder(1), testOrder(2),
                testOrder(3), testOrder(4),
                testOrder(5), testOrder(6),
                testOrder(7), testOrder(8),
                testOrder(9), testOrder(10),
                testOrder(11), testOrder(12),
                testOrder(13), testOrder(14)
        };

        Flux<Order> orderFlux = Flux.just(orders);

        OrderRepository orderRepo = Mockito.mock(OrderRepository.class);

        when(orderRepo.findAll()).thenReturn(orderFlux);

        WebTestClient testClient = WebTestClient.bindToController(new BasicController(orderRepo)).build();

        testClient.get().uri("/basic/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(String.valueOf(orders[0].getId()))
                .jsonPath("$[0].name").isEqualTo("1_name")
                .jsonPath("$[12]").doesNotExist();
    }

    @Test
    public void postOrder() {
        OrderRepository orderRepo = Mockito.mock(OrderRepository.class);
        Mono<Order> unSavedOrder = Mono.just(testOrder(0));
        Order savedOrder = testOrder(1);
        savedOrder.setId(1);
        Mono<Order> savedOrderMono = Mono.just(savedOrder);

        when(orderRepo.save(any())).thenReturn(savedOrderMono);

        WebTestClient webTestClient = WebTestClient.bindToController(new BasicController(orderRepo)).build();

        webTestClient.post()
                .uri("/basic/order")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unSavedOrder, Order.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Order.class)
                .isEqualTo(savedOrder);
    }

    public Order testOrder(Integer id) {
        if(id != null) {
            return new Order(id, String.format("%d_name", id), id * 1000);
        }else {
            return new Order(null, "null_name", 1000);
        }

    }
}
