package br.com.authservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationFieldError> fieldErrorList = e.getFieldErrors().stream().map(fieldError -> new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(new ExceptionResponse.ValidationFieldErrors(fieldErrorList));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse.ExceptionMessage("Invalid JSON body"));
    }

    @ExceptionHandler(ValidationFieldsException.class)
    public ResponseEntity<ExceptionResponse> handleValidationFieldsException(ValidationFieldsException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse.ValidationFieldErrors(e.getFieldErrors()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new ExceptionResponse.ExceptionMessage(e.getMessage()));
    }
}
