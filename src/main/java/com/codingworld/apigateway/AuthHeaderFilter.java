package com.codingworld.apigateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthHeaderFilter extends AbstractGatewayFilterFactory<AuthHeaderFilter.Config> {

    public AuthHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Extrae el encabezado de autorización de la solicitud original
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            // Si existe un encabezado de autorización, lo agrega a la solicitud hacia el backend
            if (authHeader != null) {
                exchange = exchange.mutate().request(r -> r.headers(headers -> headers.set(HttpHeaders.AUTHORIZATION, authHeader))).build();
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Puedes agregar configuraciones adicionales si es necesario
    }

}