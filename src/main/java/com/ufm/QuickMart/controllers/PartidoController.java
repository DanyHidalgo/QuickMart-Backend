package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.Partido;
import com.ufm.QuickMart.services.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    @Autowired
    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> actualizarResultado(@PathVariable Long id, @RequestBody Partido partido) {
        Partido partidoActualizado = partidoService.actualizarResultado(id, partido);
        return ResponseEntity.ok(partidoActualizado);
    }

    @GetMapping("/torneo/{torneoId}/ronda/{ronda}")
    public ResponseEntity<List<Partido>> getPartidosByTorneoIdAndRonda(
            @PathVariable Long torneoId, @PathVariable String ronda) {
        List<Partido> partidos = partidoService.getPartidosByTorneoIdAndRonda(torneoId, ronda);
        if (partidos.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay partidos
        }
        return ResponseEntity.ok(partidos);  // 200 OK si se encuentran partidos
    }
}
