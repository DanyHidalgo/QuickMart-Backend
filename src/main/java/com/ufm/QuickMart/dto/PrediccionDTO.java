package com.ufm.QuickMart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PrediccionDTO {

    @NotNull(message = "El ID del partido no puede ser nulo")
    private Long partidoId;

    @NotNull(message = "El resultado esperado no puede ser nulo")
    @Pattern(regexp = "\\d+-\\d+", message = "El resultado esperado debe estar en el formato 'golesLocal-golesVisitante'")
    private String resultadoEsperado;

    @NotNull(message = "El ID del grupo no puede ser nulo")
    private Long grupoId;

    // Getters y setters
    public Long getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Long partidoId) {
        this.partidoId = partidoId;
    }

    public String getResultadoEsperado() {
        return resultadoEsperado;
    }

    public void setResultadoEsperado(String resultadoEsperado) {
        this.resultadoEsperado = resultadoEsperado;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }
}
