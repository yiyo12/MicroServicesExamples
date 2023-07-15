package com.mcsv.hotel.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerHandlerExc {

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<Map<String, Object>>handlerResourceNotFoundExc(ResourceNotFoundExceptions rsne){

        Map rsponse = new HashMap();

        rsponse.put("Message", rsne.getMessage());
        rsponse.put("Success", false);
        rsponse.put("Status", HttpStatus.NOT_FOUND);


        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(rsponse);
    }
}
