package com.devsuperior.bds04.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodValidationException(MethodArgumentNotValidException e, HttpServletRequest servletRequest) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError();
        validationError.setTimestamp(Instant.now());
        validationError.setStatus(status.value());
        validationError.setError("validation exception!");
        validationError.setMessage(e.getMessage());
        validationError.setPath(servletRequest.getRequestURI());

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            validationError.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validationError);
    }


}
