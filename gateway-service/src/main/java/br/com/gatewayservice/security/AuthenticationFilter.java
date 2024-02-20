package br.com.gatewayservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class AuthenticationFilter implements GatewayFilter {

    @Value("${spring.security.auth.server.url}")
    private String authServerUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (this.isAuthMissing(request)) {
            return chain.filter(exchange);
        }

        String authorizationHeader = this.getAuthHeader(request).get();

        String token = authorizationHeader.replace("Bearer ", "");
        VerificationRequest verificationRequest = new VerificationRequest(token);

        return WebClient.create(authServerUrl)
                .post()
                .uri("/verify")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(verificationRequest), VerificationRequest.class)
                .exchangeToMono(response -> response.bodyToMono(VerificationResponse.class))
                .doOnSuccess(verificationResponse -> updateRequest(exchange, verificationResponse))
                .then(chain.filter(exchange));
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return getAuthHeader(request).isEmpty() || getAuthHeader(request).filter(s -> s.startsWith("Bearer ")).isEmpty();
    }

    private Optional<String> getAuthHeader(ServerHttpRequest request) {
        return Optional.ofNullable(request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
    }

    private void updateRequest(ServerWebExchange exchange, VerificationResponse verificationResponse) {
        exchange.getRequest().mutate()
                .header("email", verificationResponse.email())
                .header("role", verificationResponse.role())
                .build();
    }
}
