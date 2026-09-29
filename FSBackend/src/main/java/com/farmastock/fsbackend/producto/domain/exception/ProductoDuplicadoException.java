package com.farmastock.fsbackend.producto.domain.exception;

public class ProductoDuplicadoException extends RuntimeException {
    public ProductoDuplicadoException(String message) {
        super(message);
    }
}

