package com.ufm.QuickMart.services;

import com.ufm.QuickMart.dto.EquipoResponse;
import com.ufm.QuickMart.entities.Equipo;
import com.ufm.QuickMart.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String apiKey;

    public void fetchAndSaveEquipos(Long tournamentId) {
        String url = "https://v3.football.api-sports.io/teams?league=" + tournamentId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<EquipoResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, EquipoResponse.class);

        if (response.getBody() != null) {
            for (EquipoResponse.Team team : response.getBody().getResponse()) {
                Equipo equipo = new Equipo();
                equipo.setId(team.getId());
                equipo.setNombre(team.getName());
                equipoRepository.save(equipo);
            }
        }
    }
}
