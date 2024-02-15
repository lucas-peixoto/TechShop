package br.com.gatewayservice.config;

import br.com.gatewayservice.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthFilter authFilter;

    public GatewayConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("payment-service", r -> r.path("/api/payment/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://employee-service"))
                .route(r -> r.path("/api/v1/departments/**")
                        .uri("lb://department-service"))
                .build();
    }
}
