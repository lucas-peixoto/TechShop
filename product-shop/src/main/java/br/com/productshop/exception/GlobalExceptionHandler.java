package br.com.productshop.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationFieldError> fieldErrorList = e.getFieldErrors().stream().map(fieldError -> new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage())).toList();
        logger.error("Validation errors: {}", fieldErrorList);
        return ResponseEntity.badRequest().body(new ExceptionResponse.ValidationFieldErrors(fieldErrorList));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException() {
        logger.error("Invalid JSON body");
        return ResponseEntity.badRequest().body(new ExceptionResponse.ExceptionMessage("Invalid JSON body"));
    }

    @ExceptionHandler(ValidationFieldsException.class)
    public ResponseEntity<ExceptionResponse> handleValidationFieldsException(ValidationFieldsException e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ExceptionResponse.ValidationFieldErrors(e.getFieldErrors()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().body(new ExceptionResponse.ExceptionMessage(e.getMessage()));
    }
}
