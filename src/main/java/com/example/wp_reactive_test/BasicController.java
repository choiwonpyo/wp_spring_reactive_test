package com.example.wp_reactive_test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
public class BasicController {

    private OrderRepository orderRepository;

    public BasicController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/helloBasic")
    public Mono<String> helloBasic() {
        return Mono.just("Hello Basic");
    }

    @GetMapping("/basic/orders")
    public Flux<Order> basicOrders() {
        return orderRepository.findAll().take(12);
    }

    @PostMapping("/basic/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> postOrders(@RequestBody Order order) {
        return orderRepository.save(Objects.requireNonNull(order));
    }
}
