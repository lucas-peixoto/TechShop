package br.com.authservice.exception;

public class ValidationFieldError {

    private final String field;
    private final String message;

    public ValidationFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String field() {
        return field;
    }

    public String message() {
        return message;
    }
}
