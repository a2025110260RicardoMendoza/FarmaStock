package com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.repository;

import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.entity.CategoriaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoriaRepository extends JpaRepository<CategoriaJpaEntity, Long> {
    boolean existsByNombre(String nombre);
}
