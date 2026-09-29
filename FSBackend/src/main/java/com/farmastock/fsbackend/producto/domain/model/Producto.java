package com.farmastock.fsbackend.producto.domain.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Producto {
    private final Long id;
    private final Long categoriaId;
    private final String codigoBarras;
    private final String nombre;
    private final String descripcionCorta;
    private final BigDecimal precioVentaBase;
    private final Integer stockMinimo;
    private final Boolean estado;

    public Producto(Long id, Long categoriaId, String codigoBarras, String nombre,
                    String descripcionCorta, BigDecimal precioVentaBase, Integer stockMinimo, Boolean estado) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.precioVentaBase = precioVentaBase;
        this.stockMinimo = stockMinimo != null ? stockMinimo : 0;
        this.estado = estado != null ? estado : true;
    }

}
