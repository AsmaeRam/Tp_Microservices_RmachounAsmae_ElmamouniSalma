package com.example.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private final WebClient webClient;

    public AuthFilter(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Ignore les routes internes (Eureka, Authorization-service)
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/eureka") || path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        HttpHeaders headers = exchange.getRequest().getHeaders();
        String username = headers.getFirst("username");
        String password = headers.getFirst("password");
        String role = headers.getFirst("role");

        // Si un header est manquant → BAD REQUEST
        if (username == null || password == null || role == null) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }

        // Appel vers Authorization-Service
        return webClient.post()
                .uri("http://localhost:8090/auth/authorize")
                .headers(h -> {
                    h.set("username", username);
                    h.set("password", password);
                    h.set("role", role);
                })
                .retrieve()
                .toBodilessEntity()
                .flatMap(resp -> chain.filter(exchange)) // Si 200 OK, continuer la chaîne
                .onErrorResume(ex -> {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                });
    }

    @Override
    public int getOrder() {
        return 0; // après le filtre de log
    }
}
