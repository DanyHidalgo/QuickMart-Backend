package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.dto.PrediccionDTO;
import com.ufm.QuickMart.entities.Prediccion;
import com.ufm.QuickMart.services.PrediccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrediccionController {

    @Autowired
    private PrediccionService prediccionService;

    @PostMapping("/predicciones")
    public ResponseEntity<Prediccion> hacerPrediccion(
            @RequestParam Long usuarioId,
            @RequestBody PrediccionDTO prediccionDTO) {

        // Crear la predicción con los datos del DTO
        Prediccion prediccion = prediccionService.hacerPrediccion(usuarioId, prediccionDTO);
        return ResponseEntity.ok(prediccion);
    }

    @GetMapping("/predicciones")
    public ResponseEntity<List<Prediccion>> getPredicciones(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long partidoId,
            @RequestParam(required = false) Long grupoId) {

        List<Prediccion> predicciones;

        if (usuarioId != null && partidoId != null && grupoId != null) {
            predicciones = prediccionService.getPrediccionesPorUsuarioYGrupoYPartido(usuarioId, grupoId, partidoId);
        } else if (usuarioId != null && partidoId != null) {
            predicciones = prediccionService.getPredicciones(usuarioId, partidoId);
        } else if (usuarioId != null && grupoId != null) {
            predicciones = prediccionService.getPrediccionesPorUsuarioYGrupo(usuarioId, grupoId);
        } else if (partidoId != null && grupoId != null) {
            predicciones = prediccionService.getPrediccionesPorPartidoYGrupo(partidoId, grupoId);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(predicciones);
    }
}
