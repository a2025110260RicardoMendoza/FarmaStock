package com.farmastock.fsbackend.categoria.domain.exception;

public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException(Long id) {
        super("La categoría solicitada con ID " + id + " no existe.");
    }
}
