package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Partido;
import com.ufm.QuickMart.repositories.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;

    @Autowired
    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    public Partido actualizarResultado(Long id, Partido partidoActualizado) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        partido.setResultado(partidoActualizado.getResultado());
        partido.setGolesLocal(partidoActualizado.getGolesLocal());
        partido.setGolesVisitante(partidoActualizado.getGolesVisitante());

        return partidoRepository.save(partido);
    }

    public List<Partido> getPartidosByTorneoIdAndRonda(Long torneoId, String ronda) {
        return partidoRepository.findByTorneoIdAndRonda(torneoId, ronda);
    }
}
