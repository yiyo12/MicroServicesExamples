package com.user.service.Exceptions;

import com.user.service.Reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(ResourceNotFounfExp.class)
    public ResponseEntity<ApiResponse> handlerResourseNotFoundException(ResourceNotFounfExp resourceNf){
        String mess = resourceNf.getMessage();

        ApiResponse as = new ApiResponse().builder()
                .message(mess)
                .success(true)
                .httpStatus(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(as, HttpStatus.NOT_FOUND);
    }
}
