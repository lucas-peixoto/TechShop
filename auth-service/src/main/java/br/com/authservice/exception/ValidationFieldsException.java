package br.com.authservice.exception;

import java.util.Map;

public class ValidationFieldsException extends RuntimeException {

    private final br.com.authservice.exception.ValidationResult.FieldErrors fieldErrors;

    public ValidationFieldsException(ValidationResult.FieldErrors fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getErrors() {
        return fieldErrors.errors();
    }
}
