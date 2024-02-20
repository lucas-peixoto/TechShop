package br.com.gatewayservice.gateway;

import br.com.gatewayservice.security.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/auth/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8082")
                )
                .route(r -> r.path("/api/admin/**")
                        .filters(f -> f.stripPrefix(2).filter(authenticationFilter))
                        .uri("http://localhost:8084")
                )
                .build();
    }
}
