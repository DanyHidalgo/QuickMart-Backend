package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Equipo;
import com.ufm.QuickMart.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Cacheable(value = "equipos", key = "#torneoId")
    public List<Equipo> obtenerEquiposPorTorneo(Long torneoId) {
        return equipoRepository.findByTorneoId(torneoId);
    }

    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }
}
