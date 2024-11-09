package com.ufm.QuickMart.services;

import com.ufm.QuickMart.dto.PrediccionDTO;
import com.ufm.QuickMart.entities.Prediccion;
import com.ufm.QuickMart.repositories.PrediccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class PrediccionService {

    @Autowired
    private PrediccionRepository prediccionRepository;

    @CacheEvict(value = "predicciones", allEntries = true)
    public Prediccion hacerPrediccion(Long usuarioId, PrediccionDTO prediccionDTO) {
        Prediccion prediccion = new Prediccion();
        prediccion.setUsuarioId(usuarioId);
        prediccion.setPartidoId(prediccionDTO.getPartidoId());
        prediccion.setGrupoId(prediccionDTO.getGrupoId());
        int golesLocal = prediccionDTO.getGolesLocalEsperado();
        int golesVisitante = prediccionDTO.getGolesVisitanteEsperado();

        // Establece los valores de goles esperados
        prediccion.setGolesLocalEsperado(golesLocal);
        prediccion.setGolesVisitanteEsperado(golesVisitante);

        // Guarda la predicción en la base de datos
        return prediccionRepository.save(prediccion);
    }

    @Cacheable(value = "predicciones", key = "#usuarioId + '-' + #partidoId")
    public List<Prediccion> getPredicciones(Long usuarioId, Long partidoId) {
        return prediccionRepository.findByUsuarioIdAndPartidoId(usuarioId, partidoId);
    }

    @Cacheable(value = "predicciones", key = "#usuarioId + '-' + #grupoId")
    public List<Prediccion> getPrediccionesPorUsuarioYGrupo(Long usuarioId, Long grupoId) {
        return prediccionRepository.findByUsuarioIdAndGrupoId(usuarioId, grupoId);
    }

    @Cacheable(value = "predicciones", key = "#partidoId + '-' + #grupoId")
    public List<Prediccion> getPrediccionesPorPartidoYGrupo(Long partidoId, Long grupoId) {
        return prediccionRepository.findByPartidoIdAndGrupoId(partidoId, grupoId);
    }

    @Cacheable(value = "predicciones", key = "#usuarioId + '-' + #grupoId + '-' + #partidoId")
    public List<Prediccion> getPrediccionesPorUsuarioYGrupoYPartido(Long usuarioId, Long grupoId, Long partidoId) {
        return prediccionRepository.findByUsuarioIdAndGrupoIdAndPartidoId(usuarioId, grupoId, partidoId);
    }
}
