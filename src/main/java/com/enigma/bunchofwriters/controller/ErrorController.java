package com.enigma.bunchofwriters.controller;

import com.enigma.bunchofwriters.model.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?>responseStatusException(ResponseStatusException e){
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(e.getRawStatusCode())
                .message(e.getReason())
                .build();
        return ResponseEntity
                .status(e.getStatus())
                .body(response);
    }
}
