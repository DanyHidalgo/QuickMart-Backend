package com.ufm.QuickMart.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<UsuarioGrupo> usuarioGrupos = new HashSet<>();

    // Constructor por defecto
    public Grupo() {
    }

    // Constructor con parámetros
    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<UsuarioGrupo> getUsuarioGrupos() {
        return usuarioGrupos;
    }

    public void setUsuarioGrupos(Set<UsuarioGrupo> usuarioGrupos) {
        this.usuarioGrupos = usuarioGrupos;
    }
}
