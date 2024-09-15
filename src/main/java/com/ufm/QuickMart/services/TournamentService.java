package com.ufm.QuickMart.services;

import com.ufm.QuickMart.entities.Torneo;
import com.ufm.QuickMart.dto.TournamentResponse;
import com.ufm.QuickMart.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TournamentService {

    @Value("${API_KEY}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private EquipoService equipoService;  // Servicio para equipos

    @Autowired
    private PartidoService partidoService;  // Servicio para partidos

    public List<Torneo> fetchTournaments() {
        String url = "https://v3.football.api-sports.io/leagues?limit=50";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<TournamentResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TournamentResponse.class);

        return response.getBody().getResponse();
    }

    public void saveTournaments() {
        List<Torneo> tournaments = fetchTournaments();
        for (Torneo tournament : tournaments) {
            tournamentRepository.save(tournament);
            fetchAndSaveTeams(tournament.getId());   // Obtiene y guarda equipos
            fetchAndSaveMatches(tournament.getId()); // Obtiene y guarda partidos
        }
    }

    public void fetchAndSaveTeams(Long tournamentId) {
        equipoService.fetchAndSaveEquipos(tournamentId); // Llama al servicio de equipos
    }

    public void fetchAndSaveMatches(Long tournamentId) {
        partidoService.fetchAndSavePartidos(tournamentId); // Llama al servicio de partidos
    }
}
