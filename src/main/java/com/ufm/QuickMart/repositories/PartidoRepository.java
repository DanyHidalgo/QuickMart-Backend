package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    @Query("SELECT p FROM Partido p JOIN Equipo e ON (p.equipoLocal.id = e.id OR p.equipoVisitante.id = e.id) WHERE e.torneo.id = :torneoId AND p.ronda = :ronda")
    List<Partido> findByTorneoIdAndRonda(@Param("torneoId") Long torneoId, @Param("ronda") String ronda);
}
