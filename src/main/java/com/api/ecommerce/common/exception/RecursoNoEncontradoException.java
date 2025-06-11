package com.api.ecommerce.common.exception;

public class RecursoNoEncontradoException extends RuntimeException {
    
    public RecursoNoEncontradoException() {
       super ("no se encontró el recurso");
    }
}