package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.UsuarioGrupo;
import com.ufm.QuickMart.services.GrupoService;
import com.ufm.QuickMart.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GrupoController {


    private final UsuarioService usuarioService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    public GrupoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/grupos/agregar-usuario")
    public ResponseEntity<String> agregarUsuarioAGrupo(
            @RequestParam Long grupoId,
            @RequestParam Long usuarioId) {

        grupoService.agregarUsuarioAGrupo(grupoId, usuarioId);
        return ResponseEntity.ok("Usuario agregado al grupo exitosamente");
    }

    // Endpoint para obtener la clasificación de los usuarios en un grupo
    @GetMapping("/{grupoId}/clasificacion")
    public ResponseEntity<List<UsuarioGrupo>> obtenerClasificacionPorGrupo(@PathVariable Long grupoId) {
        List<UsuarioGrupo> clasificacion = usuarioService.obtenerClasificacionPorGrupo(grupoId);
        return ResponseEntity.ok(clasificacion);
    }
}
