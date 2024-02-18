package br.com.productadmin.exception;

import java.util.Map;

public class ValidationFieldsException extends RuntimeException {

    private ValidationResult.FieldErrors fieldErrors;

    public ValidationFieldsException(ValidationResult.FieldErrors fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getErrors() {
        return fieldErrors.errors();
    }
}
