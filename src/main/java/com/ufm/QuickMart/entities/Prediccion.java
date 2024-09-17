package com.ufm.QuickMart.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prediccion")
public class Prediccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private Long partidoId;
    private Long grupoId;

    private Integer golesLocalEsperado;
    private Integer golesVisitanteEsperado;

    // Nuevo campo para almacenar los puntos ganados
    private Integer puntosGanados;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Long partidoId) {
        this.partidoId = partidoId;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getGolesLocalEsperado() {
        return golesLocalEsperado;
    }

    public void setGolesLocalEsperado(Integer golesLocalEsperado) {
        this.golesLocalEsperado = golesLocalEsperado;
    }

    public Integer getGolesVisitanteEsperado() {
        return golesVisitanteEsperado;
    }

    public void setGolesVisitanteEsperado(Integer golesVisitanteEsperado) {
        this.golesVisitanteEsperado = golesVisitanteEsperado;
    }

    public Integer getPuntosGanados() {
        return puntosGanados;
    }

    public void setPuntosGanados(Integer puntosGanados) {
        this.puntosGanados = puntosGanados;
    }
}
