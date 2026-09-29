package com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.mapper;

import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.entity.CategoriaJpaEntity;
import com.farmastock.fsbackend.producto.domain.model.Producto;
import com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.entity.ProductoJpaEntity;

public final class ProductoPersistenceMapper {

    private ProductoPersistenceMapper() {}

    public static ProductoJpaEntity toEntity(Producto domain, CategoriaJpaEntity categoriaJpa) {
        return new ProductoJpaEntity(
                domain.getId(),
                categoriaJpa,
                domain.getCodigoBarras(),
                domain.getNombre(),
                domain.getDescripcionCorta(),
                domain.getPrecioVentaBase(),
                domain.getStockMinimo(),
                domain.getEstado()
        );
    }

    public static Producto toDomain(ProductoJpaEntity entity) {
        return new Producto(
                entity.getId(),
                entity.getCategoria().getId(),
                entity.getCodigoBarras(),
                entity.getNombre(),
                entity.getDescripcionCorta(),
                entity.getPrecioVentaBase(),
                entity.getStockMinimo(),
                entity.getEstado()
        );
    }
}