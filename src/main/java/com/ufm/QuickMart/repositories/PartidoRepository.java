package com.ufm.QuickMart.repositories;

import com.ufm.QuickMart.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
