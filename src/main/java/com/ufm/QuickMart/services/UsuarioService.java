package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Usuario;
import com.ufm.QuickMart.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public void crearUsuario(String nombre, String apellido, String nombreUsuario, String correo, String contrasena) {
        String contrasenaCifrada = passwordEncoder.encode(contrasena);
        Usuario usuario = new Usuario(nombre, apellido, nombreUsuario, correo, contrasenaCifrada);
        usuarioRepository.save(usuario);
    }
}
