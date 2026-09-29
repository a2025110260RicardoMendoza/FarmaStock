package com.farmastock.fsbackend.categoria.infrastructure.adapter.in.web.dto;

public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion,
        Boolean estado
) {}

