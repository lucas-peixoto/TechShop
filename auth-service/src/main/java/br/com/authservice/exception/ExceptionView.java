package br.com.authservice.exception;

import java.util.Map;

public sealed interface ExceptionView {
    record FieldErrorsView(Map<String, String> errors) implements ExceptionView {
    }

    record ExceptionMessageView(String message) implements ExceptionView {
    }
}
