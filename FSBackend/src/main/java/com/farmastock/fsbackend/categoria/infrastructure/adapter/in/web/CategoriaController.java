package com.farmastock.fsbackend.categoria.infrastructure.adapter.in.web;

import com.farmastock.fsbackend.categoria.domain.exception.CategoriaNoEncontradaException;
import com.farmastock.fsbackend.categoria.domain.model.Categoria;
import com.farmastock.fsbackend.categoria.domain.port.in.ConsultarCategoriaUseCase;
import com.farmastock.fsbackend.categoria.domain.port.in.RegistrarCategoriaUseCase;
import com.farmastock.fsbackend.categoria.infrastructure.adapter.in.web.dto.CategoriaResponse;
import com.farmastock.fsbackend.categoria.infrastructure.adapter.in.web.dto.CrearCategoriaRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final RegistrarCategoriaUseCase registrarUseCase;
    private final ConsultarCategoriaUseCase consultarUseCase;

    public CategoriaController(RegistrarCategoriaUseCase registrarUseCase, ConsultarCategoriaUseCase consultarUseCase) {
        this.registrarUseCase = registrarUseCase;
        this.consultarUseCase = consultarUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CrearCategoriaRequest request) {
        Categoria nueva = new Categoria(null, request.nombre(), request.descripcion(), true);
        Categoria registrada = registrarUseCase.registrar(nueva);

        CategoriaResponse response = new CategoriaResponse(
                registrada.getId(), registrada.getNombre(), registrada.getDescripcion(), registrada.getEstado()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable Long id) {
        Categoria c = consultarUseCase.buscarPorId(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException(id));
        return ResponseEntity.ok(new CategoriaResponse(c.getId(), c.getNombre(), c.getDescripcion(), c.getEstado()));
    }
}
