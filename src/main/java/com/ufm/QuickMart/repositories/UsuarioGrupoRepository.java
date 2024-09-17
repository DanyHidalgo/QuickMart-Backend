package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, Long> {
    UsuarioGrupo findByUsuarioIdAndGrupoId(Long usuarioId, Long grupoId);
    List<UsuarioGrupo> findByUsuarioId(Long usuarioId);
}
