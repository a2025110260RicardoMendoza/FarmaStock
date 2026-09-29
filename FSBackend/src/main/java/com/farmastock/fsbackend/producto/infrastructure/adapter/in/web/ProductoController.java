package com.farmastock.fsbackend.producto.infrastructure.adapter.in.web;

import com.farmastock.fsbackend.producto.domain.model.Producto;
import com.farmastock.fsbackend.producto.domain.port.in.ConsultarProductoUseCase;
import com.farmastock.fsbackend.producto.domain.port.in.RegistrarProductoUseCase;
import com.farmastock.fsbackend.producto.infrastructure.adapter.in.web.dto.CrearProductoRequest;
import com.farmastock.fsbackend.producto.infrastructure.adapter.in.web.dto.ProductoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final RegistrarProductoUseCase registrarUseCase;
    private final ConsultarProductoUseCase consultarUseCase;

    public ProductoController(RegistrarProductoUseCase registrarUseCase, ConsultarProductoUseCase consultarUseCase) {
        this.registrarUseCase = registrarUseCase;
        this.consultarUseCase = consultarUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody CrearProductoRequest request) {
        Producto nuevo = new Producto(
                null, request.categoriaId(), request.codigoBarras(), request.nombre(),
                request.descripcionCorta(), request.precioVentaBase(), request.stockMinimo(), true
        );
        Producto registrado = registrarUseCase.registrar(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(registrado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return consultarUseCase.buscarPorId(id)
                .map(p -> ResponseEntity.ok(toResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponse>> listarPorCategoria(@PathVariable Long categoriaId) {
        List<ProductoResponse> productos = consultarUseCase.listarPorCategoria(categoriaId).stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(productos);
    }

    private ProductoResponse toResponse(Producto p) {
        return new ProductoResponse(
                p.getId(), p.getCategoriaId(), p.getCodigoBarras(), p.getNombre(),
                p.getDescripcionCorta(), p.getPrecioVentaBase(), p.getStockMinimo(), p.getEstado()
        );
    }
}
