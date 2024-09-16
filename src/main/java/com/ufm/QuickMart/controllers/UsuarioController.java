package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestParam String nombre,
                                               @RequestParam String apellido,
                                               @RequestParam String nombreUsuario,
                                               @RequestParam String correo,
                                               @RequestParam String contrasena) {
        usuarioService.crearUsuario(nombre, apellido, nombreUsuario, correo, contrasena);
        return ResponseEntity.ok("Usuario creado con éxito");
    }
}
