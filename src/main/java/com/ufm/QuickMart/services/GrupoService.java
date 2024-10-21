package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Grupo;
import com.ufm.QuickMart.entities.Usuario;
import com.ufm.QuickMart.entities.UsuarioGrupo;
import com.ufm.QuickMart.repositories.GrupoRepository;
import com.ufm.QuickMart.repositories.TorneoRepository;
import com.ufm.QuickMart.repositories.UsuarioGrupoRepository;
import com.ufm.QuickMart.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TorneoRepository torneoRepository;

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
    @Transactional
    public Grupo createGrupo(String nombreGrupo, Long torneoId, Long usuarioId) {
        // Verifica si el torneo existe
        if (!torneoExists(torneoId)) {
            throw new RuntimeException("El torneo no existe");
        }

        // Crea y guarda el nuevo grupo
        Grupo grupo = new Grupo();
        grupo.setNombre(nombreGrupo);  // Usar el nombre proporcionado por el usuario
        grupo.setTorneoId(torneoRepository.findById(torneoId)
                .orElseThrow(() -> new RuntimeException("El torneo no existe")).getId()); // Asignar el objeto torneo
        grupoRepository.save(grupo);

        // Asocia el usuario al nuevo grupo
        asociarUsuarioAlGrupo(grupo, usuarioId); // Pasa el objeto grupo

        return grupo;
    }

    private boolean torneoExists(Long torneoId) {
        // Verifica si el torneo existe en la base de datos
        return torneoRepository.findById(torneoId).isPresent(); // Verifica si el torneo existe
    }

    private void asociarUsuarioAlGrupo(Grupo grupo, Long usuarioId) {
        // Verifica si el usuario existe
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        // Crear la relación en la tabla usuario_grupo
        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        usuarioGrupo.setUsuario(usuario); // Asigna el objeto usuario
        usuarioGrupo.setGrupo(grupo); // Asigna el objeto grupo

        usuarioGrupoRepository.save(usuarioGrupo);
    }

    public void deleteGrupo(Long id) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(id);
        if (grupoOptional.isPresent()) {
            grupoRepository.delete(grupoOptional.get());
        } else {
            throw new EntityNotFoundException("Grupo no encontrado");
        }
    }

}
