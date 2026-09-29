package com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence;

import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.repository.SpringDataCategoriaRepository;
import com.farmastock.fsbackend.producto.domain.model.Producto;
import com.farmastock.fsbackend.producto.domain.port.out.ProductoRepositoryPort;
import com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.entity.ProductoJpaEntity;
import com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.mapper.ProductoPersistenceMapper;
import com.farmastock.fsbackend.producto.infrastructure.adapter.out.persistence.repository.SpringDataProductoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductoPersistenceAdapter implements ProductoRepositoryPort {

    private final SpringDataProductoRepository productoRepository;
    private final SpringDataCategoriaRepository categoriaRepository;

    public ProductoPersistenceAdapter(SpringDataProductoRepository productoRepository,
                                      SpringDataCategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Producto guardar(Producto producto) {
        var categoriaRef = categoriaRepository.getReferenceById(producto.getCategoriaId());
        ProductoJpaEntity entity = ProductoPersistenceMapper.toEntity(producto, categoriaRef);
        ProductoJpaEntity saved = productoRepository.save(entity);
        return ProductoPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id).map(ProductoPersistenceMapper::toDomain);
    }

    @Override
    public List<Producto> listarPorCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoria_Id(categoriaId).stream()
                .map(ProductoPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorCodigoBarras(String codigoBarras) {
        return productoRepository.existsByCodigoBarras(codigoBarras);
    }
}

