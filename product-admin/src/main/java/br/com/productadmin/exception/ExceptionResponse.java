package br.com.productadmin.exception;

import java.util.List;

public sealed interface ExceptionResponse {
    record ValidationFieldErrors(List<ValidationFieldError> errors) implements ExceptionResponse {}
    record ExceptionMessage(String error) implements ExceptionResponse {}
}
