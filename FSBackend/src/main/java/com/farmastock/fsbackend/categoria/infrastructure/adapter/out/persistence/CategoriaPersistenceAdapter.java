package com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence;

import com.farmastock.fsbackend.categoria.domain.model.Categoria;
import com.farmastock.fsbackend.categoria.domain.port.out.CategoriaRepositoryPort;
import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.entity.CategoriaJpaEntity;
import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.mapper.CategoriaPersistenceMapper;
import com.farmastock.fsbackend.categoria.infrastructure.adapter.out.persistence.repository.SpringDataCategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoriaPersistenceAdapter implements CategoriaRepositoryPort {

    private final SpringDataCategoriaRepository repository;

    public CategoriaPersistenceAdapter(SpringDataCategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        CategoriaJpaEntity entity = CategoriaPersistenceMapper.toEntity(categoria);
        CategoriaJpaEntity saved = repository.save(entity);
        return CategoriaPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id).map(CategoriaPersistenceMapper::toDomain);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return repository.existsByNombre(nombre);
    }
}

