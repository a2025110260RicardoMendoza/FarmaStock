package com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.mapper;

import com.farmastock.fsbackend.categoria.domain.model.Categoria;
import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.entity.CategoriaJpaEntity;

public final class CategoriaPersistenceMapper {

    private CategoriaPersistenceMapper() {}

    public static CategoriaJpaEntity toEntity(Categoria domain) {
        return new CategoriaJpaEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getDescripcion(),
                domain.getEstado()
        );
    }

    public static Categoria toDomain(CategoriaJpaEntity entity) {
        return new Categoria(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getEstado()
        );
    }
}

