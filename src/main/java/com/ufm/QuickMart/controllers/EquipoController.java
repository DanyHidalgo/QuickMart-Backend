package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.Equipo;
import com.ufm.QuickMart.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/torneo/{torneoId}")
    public ResponseEntity<List<Equipo>> obtenerEquiposPorTorneo(@PathVariable Long torneoId) {
        List<Equipo> equipos = equipoService.obtenerEquiposPorTorneo(torneoId);
        return ResponseEntity.ok(equipos);
    }
}
