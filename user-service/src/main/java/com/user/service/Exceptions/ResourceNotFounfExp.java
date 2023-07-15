package com.user.service.Exceptions;

public class ResourceNotFounfExp extends RuntimeException{


    public ResourceNotFounfExp() {
        super("recurso no encontrado");
    }

    public ResourceNotFounfExp(String message) {
        super(message);
    }
}
