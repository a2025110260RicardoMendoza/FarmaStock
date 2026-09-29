package com.farmastock.fsbackend.categoria.domain.model;

import lombok.Getter;

@Getter
public class Categoria {
    private final Long id;
    private final String nombre;
    private final String descripcion;
    private final Boolean estado;

    public Categoria(Long id, String nombre, String descripcion, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado != null ? estado : true;
    }

}

