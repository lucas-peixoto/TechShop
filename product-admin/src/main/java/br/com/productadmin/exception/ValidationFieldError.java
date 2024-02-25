package br.com.productadmin.exception;

public record ValidationFieldError(String field, String message) {
}
