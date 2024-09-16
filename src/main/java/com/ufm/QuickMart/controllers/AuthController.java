package com.ufm.QuickMart.controllers;

import com.ufm.QuickMart.entities.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Aquí puedes agregar la lógica para autenticar al usuario si es necesario
        return ResponseEntity.ok("Login successful");
    }
}
