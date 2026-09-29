package com.farmastock.fsbackend.shared.infrastructure.web;

import com.farmastock.fsbackend.categoria.domain.exception.CategoriaDuplicadaException;
import com.farmastock.fsbackend.categoria.domain.exception.CategoriaNoEncontradaException;
import com.farmastock.fsbackend.producto.domain.exception.ProductoDuplicadoException;
import com.farmastock.fsbackend.shared.infrastructure.web.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errores.put(err.getField(), err.getDefaultMessage())
        );

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error de Validación HTTP",
                "La solicitud contiene campos con formato o valores inválidos",
                errores
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({CategoriaDuplicadaException.class, ProductoDuplicadoException.class})
    public ResponseEntity<ApiErrorResponse> manejarDuplicados(RuntimeException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflicto de Negocio",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<ApiErrorResponse> manejarNoEncontrado(CategoriaNoEncontradaException ex) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso No Encontrado",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
