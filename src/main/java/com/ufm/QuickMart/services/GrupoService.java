package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Grupo;
import com.ufm.QuickMart.entities.Usuario;
import com.ufm.QuickMart.entities.UsuarioGrupo;
import com.ufm.QuickMart.repositories.GrupoRepository;
import com.ufm.QuickMart.repositories.UsuarioGrupoRepository;
import com.ufm.QuickMart.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioGrupoRepository usuarioGrupoRepository;

    public void agregarUsuarioAGrupo(Long grupoId, Long usuarioId) {
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verifica si ya existe la relación antes de agregar
        UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findByUsuarioIdAndGrupoId(usuarioId, grupoId);
        if (usuarioGrupo == null) {
            usuarioGrupo = new UsuarioGrupo();
            usuarioGrupo.setUsuario(usuario);
            usuarioGrupo.setGrupo(grupo);
            usuarioGrupoRepository.save(usuarioGrupo);
        }
    }
}
