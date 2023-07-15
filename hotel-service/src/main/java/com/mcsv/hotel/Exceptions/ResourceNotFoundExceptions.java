package com.mcsv.hotel.Exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{


    public ResourceNotFoundExceptions() {
        super("recurso no encontrado en el servidor");
    }

    public ResourceNotFoundExceptions(String message) {
        super(message);
    }
}
