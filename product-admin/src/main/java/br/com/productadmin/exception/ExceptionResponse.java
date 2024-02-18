package br.com.productadmin.exception;

import java.util.Map;

public sealed interface ExceptionResponse {
    record FieldErrors(Map<String, String> errors) implements ExceptionResponse {}
    record ExceptionMessage(String error) implements ExceptionResponse {}
}
