package com.farmastock.fsbackend.categoria.domain.port.out;

import com.farmastock.fsbackend.categoria.domain.model.Categoria;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    Categoria guardar(Categoria categoria);
    Optional<Categoria> buscarPorId(Long id);
    boolean existePorNombre(String nombre);
}
