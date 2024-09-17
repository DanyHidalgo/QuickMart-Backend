package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Prediccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrediccionRepository extends JpaRepository<Prediccion, Long> {
    List<Prediccion> findByUsuarioIdAndPartidoId(Long usuarioId, Long partidoId);
    List<Prediccion> findByUsuarioIdAndGrupoId(Long usuarioId, Long grupoId);
    List<Prediccion> findByPartidoIdAndGrupoId(Long partidoId, Long grupoId);
    List<Prediccion> findByUsuarioIdAndGrupoIdAndPartidoId(Long usuarioId, Long grupoId, Long partidoId);
}
