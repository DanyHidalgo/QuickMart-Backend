package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    @Query("SELECT g FROM Grupo g WHERE g.torneoId = :torneoId AND g.nombre = :nombre")
    Optional<Grupo> findByTorneoIdAndNombre(@Param("torneoId") Long torneoId, @Param("nombre") String nombre);
}
