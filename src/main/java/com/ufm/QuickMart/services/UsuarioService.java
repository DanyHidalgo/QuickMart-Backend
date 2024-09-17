package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Grupo;
import com.ufm.QuickMart.entities.Usuario;
import com.ufm.QuickMart.entities.UsuarioGrupo;
import com.ufm.QuickMart.repositories.UsuarioRepository;
import com.ufm.QuickMart.repositories.GrupoRepository;
import com.ufm.QuickMart.repositories.UsuarioGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, GrupoRepository grupoRepository,
                          UsuarioGrupoRepository usuarioGrupoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
        this.usuarioGrupoRepository = usuarioGrupoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Busca un usuario por su nombre de usuario
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    // Crea un nuevo usuario con la contraseña cifrada
    public void crearUsuario(String nombre, String apellido, String nombreUsuario, String correo, String contrasena) {
        String contrasenaCifrada = passwordEncoder.encode(contrasena);
        Usuario usuario = new Usuario(nombre, apellido, nombreUsuario, correo, contrasenaCifrada);
        usuarioRepository.save(usuario);
    }

    // Agrega un usuario a un grupo, solo si no existe la relación
    public void agregarUsuarioAGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        // Verifica si ya existe la relación
        UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findByUsuarioIdAndGrupoId(usuarioId, grupoId);
        if (usuarioGrupo == null) {
            usuarioGrupo = new UsuarioGrupo();
            usuarioGrupo.setUsuario(usuario);
            usuarioGrupo.setGrupo(grupo);
            usuarioGrupoRepository.save(usuarioGrupo);
        }
    }

    // Método actualizado para obtener los grupos del usuario
    public Set<Grupo> obtenerGruposDelUsuario(Long usuarioId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Consultar las relaciones de UsuarioGrupo por usuario_id
        List<UsuarioGrupo> usuarioGrupos = usuarioGrupoRepository.findByUsuarioId(usuarioId);

        // Extraer los grupos de las relaciones UsuarioGrupo
        Set<Grupo> grupos = new HashSet<>();
        for (UsuarioGrupo usuarioGrupo : usuarioGrupos) {
            grupos.add(usuarioGrupo.getGrupo());
        }

        return grupos;
    }
}
