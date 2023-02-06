package com.javedrpi.webfluxdemo.exception.handler;

import com.javedrpi.webfluxdemo.dto.InputFailedValidationResponse;
import com.javedrpi.webfluxdemo.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex){
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
