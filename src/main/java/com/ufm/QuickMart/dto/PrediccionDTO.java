package com.ufm.QuickMart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PrediccionDTO {

    @NotNull(message = "El ID del partido no puede ser nulo")
    private Long partidoId;

    @NotNull(message = "El ID del grupo no puede ser nulo")
    private Long grupoId;

    @NotNull(message = "Los goles esperados del equipo local no pueden ser nulos")
    @Min(value = 0, message = "Los goles del equipo local deben ser un número positivo")
    private Integer golesLocalEsperado;

    @NotNull(message = "Los goles esperados del equipo visitante no pueden ser nulos")
    @Min(value = 0, message = "Los goles del equipo visitante deben ser un número positivo")
    private Integer golesVisitanteEsperado;

    // Getters y setters
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
}
