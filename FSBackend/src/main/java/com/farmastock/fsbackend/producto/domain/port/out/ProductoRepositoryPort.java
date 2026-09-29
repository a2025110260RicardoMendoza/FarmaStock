package com.farmastock.fsbackend.producto.domain.port.out;

import com.farmastock.fsbackend.producto.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    Producto guardar(Producto producto);
    Optional<Producto> buscarPorId(Long id);
    List<Producto> listarPorCategoriaId(Long categoriaId);
    boolean existePorCodigoBarras(String codigoBarras);
}

