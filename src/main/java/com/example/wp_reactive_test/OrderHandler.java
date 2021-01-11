package com.example.wp_reactive_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class OrderHandler {
    @Autowired
    private OrderRepository orderRepository;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Order.class)
                .flatMap(orderRepository::save)
                .flatMap(o ->
                    ServerResponse.created(URI.create("/orders/" + o.getId()))
                            .build()
                );
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return ServerResponse.ok().body(orderRepository.findAll(), Order.class);
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        return ServerResponse.ok().body(orderRepository.findOne(Integer.parseInt(request.pathVariable("id"))), Order.class);
    }
}
