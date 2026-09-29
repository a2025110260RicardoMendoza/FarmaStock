package com.farmastock.fsbackend.producto.infrastructure.adapter.in.web.dto;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        Long categoriaId,
        String codigoBarras,
        String nombre,
        String descripcionCorta,
        BigDecimal precioVentaBase,
        Integer stockMinimo,
        Boolean estado
) {}

