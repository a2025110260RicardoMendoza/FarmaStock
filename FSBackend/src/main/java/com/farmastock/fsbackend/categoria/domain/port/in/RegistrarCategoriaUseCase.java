package com.farmastock.fsbackend.categoria.domain.port.in;

import com.farmastock.fsbackend.categoria.domain.model.Categoria;

public interface RegistrarCategoriaUseCase {
    Categoria registrar(Categoria categoria);
}