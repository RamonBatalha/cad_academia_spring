package com.academia.rb.cadastro_academia.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.academia.rb.cadastro_academia.model.error.ErrorMessage;
import com.academia.rb.cadastro_academia.view.Controller.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException (ResourceNotFoundException ex) {

        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
    }
}
