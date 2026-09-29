package com.farmastock.fsbackend.categoria.application.service;

import com.farmastock.fsbackend.categoria.domain.exception.CategoriaDuplicadaException;
import com.farmastock.fsbackend.categoria.domain.model.Categoria;
import com.farmastock.fsbackend.categoria.domain.port.in.ConsultarCategoriaUseCase;
import com.farmastock.fsbackend.categoria.domain.port.in.RegistrarCategoriaUseCase;
import com.farmastock.fsbackend.categoria.domain.port.out.CategoriaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoriaService implements RegistrarCategoriaUseCase, ConsultarCategoriaUseCase {

    private final CategoriaRepositoryPort repositoryPort;

    public CategoriaService(CategoriaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public Categoria registrar(Categoria categoria) {
        if (repositoryPort.existePorNombre(categoria.getNombre())) {
            throw new CategoriaDuplicadaException("Ya existe una categoría registrada con el nombre: " + categoria.getNombre());
        }
        return repositoryPort.guardar(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> buscarPorId(Long id) {
        return repositoryPort.buscarPorId(id);
    }
}
