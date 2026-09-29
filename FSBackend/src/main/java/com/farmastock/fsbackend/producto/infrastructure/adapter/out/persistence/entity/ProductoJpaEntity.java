package com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.entity;

import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.entity.CategoriaJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "productos", schema = "public")
public class ProductoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaJpaEntity categoria;

    @Column(name = "codigo_barras", nullable = false, unique = true, length = 100)
    private String codigoBarras;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion_corta", length = 255)
    private String descripcionCorta;

    @Column(name = "precio_venta_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVentaBase;

    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;

    @Column(name = "estado")
    private Boolean estado;

    protected ProductoJpaEntity() {}

    public ProductoJpaEntity(Long id, CategoriaJpaEntity categoria, String codigoBarras, String nombre,
                             String descripcionCorta, BigDecimal precioVentaBase, Integer stockMinimo, Boolean estado) {
        this.id = id;
        this.categoria = categoria;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.precioVentaBase = precioVentaBase;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
    }

}
