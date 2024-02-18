package br.com.authservice.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionView> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(new ExceptionView.FieldErrorsView(errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionView> handleHttpMessageNotReadableException() {
        return ResponseEntity.badRequest().body(new ExceptionView.ExceptionMessageView("Invalid JSON body"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionView> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new ExceptionView.ExceptionMessageView(e.getMessage()));
    }

//    @ExceptionHandler(DuplicateKeyException.class)
//    public ResponseEntity<ExceptionView> handleDuplicateKeyException(DuplicateKeyException e) {
//        return ResponseEntity.badRequest().body(new ExceptionView.ExceptionMessageView(e.getMessage()));
//    }
}
