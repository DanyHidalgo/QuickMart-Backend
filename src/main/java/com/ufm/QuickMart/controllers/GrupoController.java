package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping("/grupos/agregar-usuario")
    public ResponseEntity<String> agregarUsuarioAGrupo(
            @RequestParam Long grupoId,
            @RequestParam Long usuarioId) {

        grupoService.agregarUsuarioAGrupo(grupoId, usuarioId);
        return ResponseEntity.ok("Usuario agregado al grupo exitosamente");
    }
}
