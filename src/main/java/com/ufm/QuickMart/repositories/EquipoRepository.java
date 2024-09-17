package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    // Método para encontrar equipos por torneo
    List<Equipo> findByTorneoId(Long torneoId);
}
