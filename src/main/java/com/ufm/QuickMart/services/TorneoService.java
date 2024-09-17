package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Equipo;
import com.ufm.QuickMart.entities.Torneo;
import com.ufm.QuickMart.repositories.EquipoRepository;
import com.ufm.QuickMart.repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneoService {

    private final TorneoRepository torneoRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    public TorneoService(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    // Método para obtener todos los torneos
    public List<Torneo> getAllTorneos() {
        return torneoRepository.findAll();
    }

    public List<Equipo> obtenerEquiposPorTorneo(Long torneoId) {
        return equipoRepository.findByTorneoId(torneoId);
    }
}
