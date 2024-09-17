package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.dto.PrediccionDTO;
import com.ufm.QuickMart.entities.Prediccion;
import com.ufm.QuickMart.services.PrediccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
