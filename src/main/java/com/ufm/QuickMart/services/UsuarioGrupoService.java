package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.UsuarioGrupo;
import com.ufm.QuickMart.repositories.UsuarioGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioGrupoService {

    @Autowired
    private UsuarioGrupoRepository usuarioGrupoRepository;

    public UsuarioGrupo saveUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
        return usuarioGrupoRepository.save(usuarioGrupo);
    }

    public Optional<UsuarioGrupo> getUsuarioGrupoById(Long id) {
        return usuarioGrupoRepository.findById(id);
    }
}
