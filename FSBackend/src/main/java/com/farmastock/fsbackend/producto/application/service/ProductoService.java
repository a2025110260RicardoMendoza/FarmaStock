package com.farmastock.fsbackend.producto.application.service;

import com.farmastock.fsbackend.categoria.domain.exception.CategoriaNoEncontradaException;
import com.farmastock.fsbackend.categoria.domain.port.in.ConsultarCategoriaUseCase;
import com.farmastock.fsbackend.producto.domain.exception.ProductoDuplicadoException;
import com.farmastock.fsbackend.producto.domain.model.Producto;
import com.farmastock.fsbackend.producto.domain.port.in.ConsultarProductoUseCase;
import com.farmastock.fsbackend.producto.domain.port.in.RegistrarProductoUseCase;
import com.farmastock.fsbackend.producto.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements RegistrarProductoUseCase, ConsultarProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final ConsultarCategoriaUseCase consultarCategoriaUseCase;

    public ProductoService(ProductoRepositoryPort productoRepositoryPort,
                           ConsultarCategoriaUseCase consultarCategoriaUseCase) {
        this.productoRepositoryPort = productoRepositoryPort;
        this.consultarCategoriaUseCase = consultarCategoriaUseCase;
    }

    @Override
    @Transactional
    public Producto registrar(Producto producto) {
        if (consultarCategoriaUseCase.buscarPorId(producto.getCategoriaId()).isEmpty()) {
            throw new CategoriaNoEncontradaException(producto.getCategoriaId());
        }

        if (productoRepositoryPort.existePorCodigoBarras(producto.getCodigoBarras())) {
            throw new ProductoDuplicadoException("Ya existe un producto con el código de barras: " + producto.getCodigoBarras());
        }

        return productoRepositoryPort.guardar(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepositoryPort.buscarPorId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarPorCategoria(Long categoriaId) {
        return productoRepositoryPort.listarPorCategoriaId(categoriaId);
    }
}

