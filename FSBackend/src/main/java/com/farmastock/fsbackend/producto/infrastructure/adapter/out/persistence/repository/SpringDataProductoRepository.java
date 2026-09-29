package com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.repository;

import com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.entity.ProductoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProductoRepository extends JpaRepository<ProductoJpaEntity, Long> {
    boolean existsByCodigoBarras(String codigoBarras);
    List<ProductoJpaEntity> findByCategoria_Id(Long categoriaId);
}

