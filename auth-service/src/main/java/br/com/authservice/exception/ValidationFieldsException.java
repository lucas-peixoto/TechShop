package br.com.authservice.exception;

import java.util.List;

public class ValidationFieldsException extends RuntimeException {

    private List<ValidationFieldError> fieldErrors;

    public ValidationFieldsException(List<ValidationFieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<ValidationFieldError> getFieldErrors() {
        return fieldErrors;
    }
}
