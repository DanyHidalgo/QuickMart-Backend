package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.UsuarioGrupo;
import com.ufm.QuickMart.services.UsuarioGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario-grupo")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioGrupoService usuarioGrupoService;

    @PostMapping
    public UsuarioGrupo createUsuarioGrupo(@RequestBody UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoService.saveUsuarioGrupo(usuarioGrupo);
    }

    @GetMapping("/{id}")
    public Optional<UsuarioGrupo> getUsuarioGrupoById(@PathVariable Long id) {
        return usuarioGrupoService.getUsuarioGrupoById(id);
    }
}
