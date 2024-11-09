package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.*;
import com.ufm.QuickMart.repositories.PrediccionRepository;
import com.ufm.QuickMart.repositories.UsuarioRepository;
import com.ufm.QuickMart.repositories.GrupoRepository;
import com.ufm.QuickMart.repositories.UsuarioGrupoRepository;
import com.ufm.QuickMart.repositories.PartidoRepository;  // Asegúrate de tener este repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PrediccionRepository prediccionRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PartidoRepository partidoRepository;  // Repositorio para acceder a los partidos
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PrediccionRepository prediccionRepository,
                          GrupoRepository grupoRepository, UsuarioGrupoRepository usuarioGrupoRepository,
                          PartidoRepository partidoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.prediccionRepository = prediccionRepository;
        this.grupoRepository = grupoRepository;
        this.usuarioGrupoRepository = usuarioGrupoRepository;
        this.partidoRepository = partidoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Busca un usuario por su nombre de usuario
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    // Crea un nuevo usuario con la contraseña cifrada
    public void crearUsuario(String nombre, String apellido, String nombreUsuario, String correo, String contrasena) {
        String contrasenaCifrada = passwordEncoder.encode(contrasena);
        Usuario usuario = new Usuario(nombre, apellido, nombreUsuario, correo, contrasenaCifrada);
        usuarioRepository.save(usuario);
    }

    // Agrega un usuario a un grupo, solo si no existe la relación
    public void agregarUsuarioAGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        // Verifica si ya existe la relación
        UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findByUsuarioIdAndGrupoId(usuarioId, grupoId);
        if (usuarioGrupo == null) {
            usuarioGrupo = new UsuarioGrupo();
            usuarioGrupo.setUsuario(usuario);
            usuarioGrupo.setGrupo(grupo);
            usuarioGrupoRepository.save(usuarioGrupo);
        }
    }

    // Método actualizado para obtener los grupos del usuario
    @Cacheable(value = "gruposUsuario", key = "#usuarioId")
    public Set<Grupo> obtenerGruposDelUsuario(Long usuarioId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Consultar las relaciones de UsuarioGrupo por usuario_id
        List<UsuarioGrupo> usuarioGrupos = usuarioGrupoRepository.findByUsuarioId(usuarioId);

        // Extraer los grupos de las relaciones UsuarioGrupo
        Set<Grupo> grupos = new HashSet<>();
        for (UsuarioGrupo usuarioGrupo : usuarioGrupos) {
            grupos.add(usuarioGrupo.getGrupo());
        }

        return grupos;
    }

    // Método para obtener la clasificación por grupo
    public List<UsuarioGrupo> obtenerClasificacionPorGrupo(Long grupoId) {
        return usuarioGrupoRepository.findByGrupoIdOrderByPuntajeDesc(grupoId);
    }

    // Calcula los puntos para una predicción y un partido
    public int calcularPuntos(Prediccion prediccion, Partido partido) {
        int puntos = 0;

        if (partido.getGolesLocal() >= 0 && partido.getGolesVisitante() >= 0) {
            // Verifica el ganador
            if ((partido.getGolesLocal() > partido.getGolesVisitante() && prediccion.getGolesLocalEsperado() > prediccion.getGolesVisitanteEsperado())
                    || (partido.getGolesLocal() < partido.getGolesVisitante() && prediccion.getGolesLocalEsperado() < prediccion.getGolesVisitanteEsperado())
                    || (partido.getGolesLocal() == partido.getGolesVisitante() && prediccion.getGolesLocalEsperado() == prediccion.getGolesVisitanteEsperado())) {
                puntos += 10;
            }

            // Verifica goles visitantes
            if (partido.getGolesVisitante() == prediccion.getGolesVisitanteEsperado()) {
                puntos += 8;
            }

            // Verifica goles locales
            if (partido.getGolesLocal() == prediccion.getGolesLocalEsperado()) {
                puntos += 8;
            }

            // Verifica predicción exacta
            if (partido.getGolesLocal() == prediccion.getGolesLocalEsperado()
                    && partido.getGolesVisitante() == prediccion.getGolesVisitanteEsperado()) {
                puntos = 30;
            }
        }

        return puntos;
    }

    // Actualiza los puntos de los usuarios basados en el resultado del partido
    public void actualizarPuntos(Long partidoId) {
        System.out.println("Buscando partido con ID: " + partidoId);
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        System.out.println("Partido encontrado: " + partido);

        List<Prediccion> predicciones = prediccionRepository.findByPartidoId(partidoId);
        System.out.println("Número de predicciones encontradas: " + predicciones.size());

        for (Prediccion prediccion : predicciones) {
            int puntos = calcularPuntos(prediccion, partido);
            System.out.println("Predicción: " + prediccion + ", Puntos: " + puntos);

            UsuarioGrupo usuarioGrupo = usuarioGrupoRepository.findByUsuarioIdAndGrupoId(prediccion.getUsuarioId(), prediccion.getGrupoId());

            if (usuarioGrupo != null) {
                usuarioGrupo.setPuntaje(usuarioGrupo.getPuntaje() + puntos);
                usuarioGrupoRepository.save(usuarioGrupo);

                prediccion.setPuntosGanados(puntos);
                prediccionRepository.save(prediccion);
            } else {
                System.out.println("No se encontró UsuarioGrupo para Usuario ID: " + prediccion.getUsuarioId() + " y Grupo ID: " + prediccion.getGrupoId());
            }
        }
    }

}
