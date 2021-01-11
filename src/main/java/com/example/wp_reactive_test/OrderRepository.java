package com.example.wp_reactive_test;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OrderRepository {
    Map<Integer, Order> mockTable = new ConcurrentHashMap<>();
    final AtomicInteger AUTO_ID = new AtomicInteger(0);
    public Mono<Order> save(Order order) {
        int id = AUTO_ID.incrementAndGet();
        order.setId(id);
        mockTable.put(id, order);
        return Mono.justOrEmpty(order);
    }

    public Flux<Order> findAll() {
        return Flux.just(mockTable.values().toArray(new Order[0]));
    }

    public Mono<Order> findOne(int id) {
        return Mono.just(mockTable.get(id));
    }
}
