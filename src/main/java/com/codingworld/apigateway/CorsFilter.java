package com.codingworld.apigateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CorsFilter extends AbstractGatewayFilterFactory<CorsFilter.Config> {

    public CorsFilter() {
        super(Config.class);
    }

    public static class Config {
        // Configuraciones adicionales si es necesario
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:4200");
            response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, PATCH, OPTIONS");
            response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
            response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Otros encabezados si es necesario
            }));
        };
    }
}
