package br.com.authservice.exception;

public class ValidationMessageException extends RuntimeException {

    private br.com.authservice.exception.ValidationResult.ErrorMessage errorMessage;

    public ValidationMessageException(ValidationResult.ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage.error();
    }
}
