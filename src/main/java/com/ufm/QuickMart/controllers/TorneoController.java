package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.Equipo;
import com.ufm.QuickMart.entities.Torneo;
import com.ufm.QuickMart.services.TorneoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping
    public ResponseEntity<List<Torneo>> getAllTorneos() {
        List<Torneo> torneos = torneoService.getAllTorneos();
        return ResponseEntity.ok(torneos);
    }

    @GetMapping("/torneos/{torneoId}/equipos")
    public ResponseEntity<List<Equipo>> obtenerEquiposPorTorneo(@PathVariable Long torneoId) {
        List<Equipo> equipos = torneoService.obtenerEquiposPorTorneo(torneoId);
        return ResponseEntity.ok(equipos);
    }
}
