package com.farmastock.fsbackend.categoria.domain.port.in;

import com.farmastock.fsbackend.categoria.domain.model.Categoria;
import java.util.Optional;

public interface ConsultarCategoriaUseCase {
    Optional<Categoria> buscarPorId(Long id);
}

