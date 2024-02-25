package br.com.productshop.exception;

public record ValidationFieldError(String field, String message) {
}
