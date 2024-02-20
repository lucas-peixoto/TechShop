package br.com.authservice.exception;

import java.util.List;

public sealed interface ExceptionResponse {
    record ValidationFieldErrors(List<ValidationFieldError> errors) implements ExceptionResponse {
    }

    record ExceptionMessage(String message) implements ExceptionResponse {
    }
}
