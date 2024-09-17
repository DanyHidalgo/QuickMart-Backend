package com.ufm.QuickMart.services;

import com.ufm.QuickMart.dto.PrediccionDTO;
import com.ufm.QuickMart.entities.Prediccion;
import com.ufm.QuickMart.repositories.PrediccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrediccionService {

    @Autowired
    private PrediccionRepository prediccionRepository;

    public Prediccion hacerPrediccion(Long usuarioId, PrediccionDTO prediccionDTO) {
        Prediccion prediccion = new Prediccion();
        prediccion.setUsuarioId(usuarioId);
        prediccion.setPartidoId(prediccionDTO.getPartidoId());
        prediccion.setGrupoId(prediccionDTO.getGrupoId());
        prediccion.setResultadoEsperado(prediccionDTO.getResultadoEsperado());

        // Guarda la predicción en la base de datos
        return prediccionRepository.save(prediccion);
    }
}
