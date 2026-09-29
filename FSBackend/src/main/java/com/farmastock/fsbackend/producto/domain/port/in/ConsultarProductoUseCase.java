package com.farmastock.fsbackend.producto.domain.port.in;

import com.farmastock.fsbackend.producto.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ConsultarProductoUseCase {
    Optional<Producto> buscarPorId(Long id);
    List<Producto> listarPorCategoria(Long categoriaId);
}

