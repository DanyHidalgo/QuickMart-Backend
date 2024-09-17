package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoRepository extends JpaRepository<Torneo, Long> {
    // Puedes agregar métodos personalizados si los necesitas
}
