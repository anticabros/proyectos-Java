package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

public class UsuarioModel {
    private final SimpleStringProperty idUsuario;
    private final SimpleStringProperty nombre;

    public UsuarioModel(String idUsuario, String nombre) {
        this.idUsuario = new SimpleStringProperty(idUsuario);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getIdUsuario() {
        return idUsuario.get();
    }

    public String getNombre() {
        return nombre.get();
    }
}
