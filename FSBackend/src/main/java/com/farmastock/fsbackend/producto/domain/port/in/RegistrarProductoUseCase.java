package com.farmastock.fsbackend.producto.domain.port.in;

import com.farmastock.fsbackend.producto.domain.model.Producto;

public interface RegistrarProductoUseCase {
    Producto registrar(Producto producto);
}

