package br.com.productadmin.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private final List<ValidationFieldError> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public void addError(String field, String message) {
        this.errors.add(new ValidationFieldError(field, message));
    }

    public void throwIfInvalid() {
        if (!errors.isEmpty()) {
            throw new ValidationFieldsException(this.errors);
        }
    }
}
