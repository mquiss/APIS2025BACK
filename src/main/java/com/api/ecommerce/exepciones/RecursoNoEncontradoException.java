package com.api.ecommerce.exepciones;

public class RecursoNoEncontradoException extends RuntimeException {
    
    public RecursoNoEncontradoException() {
       super ("no se encontró el recurso");
    }
}