package com.example.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RequestResponseLoggingFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String method = exchange.getRequest().getMethod() != null
                ? exchange.getRequest().getMethod().name()
                : "UNKNOWN";
        String uri = exchange.getRequest().getURI().toString();

        log.info(" [GATEWAY PRE] {} {}", method, uri);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Integer status = exchange.getResponse().getStatusCode() != null
                    ? exchange.getResponse().getStatusCode().value()
                    : -1;

            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            String routeId = route != null ? route.getId() : "unknown-route";

            log.info(" [GATEWAY POST] {} -> status={} via route={}", uri, status, routeId);
        }));
    }



    @Override
    public int getOrder() {
        return -1; // exécuter tôt
    }
}
