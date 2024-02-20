package br.com.authservice.exception;

public record ValidationFieldError(String field, String message) {
}
