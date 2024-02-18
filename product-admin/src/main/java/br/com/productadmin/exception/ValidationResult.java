package br.com.productadmin.exception;

import java.util.Map;

public sealed interface ValidationResult {

    void throwIfInvalid();

    record FieldErrors(Map<String, String> errors) implements ValidationResult {
        @Override
        public void throwIfInvalid() {
            throw new ValidationFieldsException(this);
        }
    }

    record ErrorMessage(String error) implements ValidationResult {
        @Override
        public void throwIfInvalid() {
            throw new ValidationMessageException(this);
        }
    }

    record Success() implements ValidationResult {
        @Override
        public void throwIfInvalid() {
            // Do nothing
        }
    }
}
