package com.farmastock.fsbackend.producto.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CrearProductoRequest(
        @NotNull(message = "El ID de la categoría es obligatorio")
        Long categoriaId,

        @NotBlank(message = "El código de barras es obligatorio")
        @Size(max = 100, message = "El código de barras no puede exceder los 100 caracteres")
        String codigoBarras,

        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 150, message = "El nombre no puede exceder los 150 caracteres")
        String nombre,

        @Size(max = 255, message = "La descripción corta no puede exceder los 255 caracteres")
        String descripcionCorta,

        @NotNull(message = "El precio de venta base es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        BigDecimal precioVentaBase,

        @NotNull(message = "El stock mínimo es obligatorio")
        @Min(value = 0, message = "El stock mínimo no puede ser negativo")
        Integer stockMinimo
) {}

