package br.com.productadmin.exception;

public class ValidationMessageException extends RuntimeException {

    private ValidationResult.ErrorMessage errorMessage;

    public ValidationMessageException(ValidationResult.ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage.error();
    }
}
