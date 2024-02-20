package br.com.gatewayservice.security;

public record VerificationResponse(String email, String role, boolean active) {
}
