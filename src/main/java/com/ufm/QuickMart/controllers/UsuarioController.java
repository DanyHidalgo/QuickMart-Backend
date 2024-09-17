package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.Grupo;
import com.ufm.QuickMart.services.UsuarioService;
import com.ufm.QuickMart.services.GrupoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final GrupoService grupoService;

    public UsuarioController(UsuarioService usuarioService, GrupoService grupoService) {
        this.usuarioService = usuarioService;
        this.grupoService = grupoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(
            @RequestParam @NotNull @Size(min = 1, max = 50) String nombre,
            @RequestParam @NotNull @Size(min = 1, max = 50) String apellido,
            @RequestParam @NotNull @Size(min = 3, max = 20) String nombreUsuario,
            @RequestParam @NotNull @Size(min = 5, max = 50) String correo,
            @RequestParam @NotNull @Size(min = 6, max = 100) String contrasena) {
        try {
            usuarioService.crearUsuario(nombre, apellido, nombreUsuario, correo, contrasena);
            return ResponseEntity.ok("Usuario creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear usuario: " + e.getMessage());
        }
    }

    @PostMapping("/{usuarioId}/grupos/{grupoId}")
    public ResponseEntity<String> agregarUsuarioAGrupo(
            @PathVariable @NotNull Long usuarioId,
            @PathVariable @NotNull Long grupoId) {
        try {
            usuarioService.agregarUsuarioAGrupo(usuarioId, grupoId);
            return ResponseEntity.ok("Usuario agregado al grupo con éxito");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Grupo no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/{usuarioId}/grupos")
    public ResponseEntity<?> obtenerGruposDelUsuario(@PathVariable @NotNull Long usuarioId) {
        try {
            Set<Grupo> grupos = usuarioService.obtenerGruposDelUsuario(usuarioId);
            return ResponseEntity.ok(grupos);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }
}
