package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("product-composite", r -> r.path("/product-composite/**")
                .uri("lb://product-composite"))
            .route("product", r -> r.path("/products/**")
                .uri("lb://product-service"))
            .route("review", r -> r.path("/reviews/**")
                .uri("lb://review-service"))
            .route("recommendation", r -> r.path("/recommendations/**")
                .uri("lb://recommendation-service"))
            .build();
    }
}
