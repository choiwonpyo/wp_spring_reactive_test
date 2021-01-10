package com.example.wp_reactive_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class WpReactiveTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpReactiveTestApplication.class, args);
    }


    //RouterFunction Bean 을 만들었다.
    @Bean
    public RouterFunction<ServerResponse> routes(
            OrderHandler handler
    ) {
        return
                RouterFunctions.nest(
                        RequestPredicates.path("/orders"),
                        RouterFunctions.nest(
                                RequestPredicates.accept(MediaType.APPLICATION_JSON),
                                RouterFunctions.route(RequestPredicates.GET("/{id}"), handler::get)
                                        .andRoute(RequestPredicates.method(HttpMethod.GET), handler::list)
                        )
                                .andNest(RequestPredicates.contentType(MediaType.APPLICATION_JSON),
                                        RouterFunctions.route(RequestPredicates.POST(""), handler::create)
                                )
                );
    }

    @Bean
    public RouterFunction<ServerResponse> routes2(OrderHandler handler)  {
        return RouterFunctions.nest(
                RequestPredicates.path("/"),
                RouterFunctions.route(RequestPredicates.GET(""), (ServerRequest request) -> ServerResponse.ok().bodyValue("HelloWorld"))
                );
    }
}
