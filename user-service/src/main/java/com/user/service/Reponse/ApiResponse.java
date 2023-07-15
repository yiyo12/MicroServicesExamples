package com.user.service.Reponse;

import lombok.*;
import org.springframework.http.HttpStatus;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus httpStatus;
}
