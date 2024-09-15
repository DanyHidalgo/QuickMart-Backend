package com.ufm.QuickMart.services;

import com.ufm.QuickMart.dto.PartidoResponse;
import com.ufm.QuickMart.entities.Partido;
import com.ufm.QuickMart.repositories.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String apiKey;

    public void fetchAndSavePartidos(Long tournamentId) {
        String url = "https://v3.football.api-sports.io/fixtures?league=" + tournamentId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<PartidoResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, PartidoResponse.class);

        if (response.getBody() != null) {
            for (PartidoResponse.Fixture fixture : response.getBody().getResponse()) {
                Partido partido = new Partido();
                partido.setId(fixture.getId());
                partido.setRonda(fixture.getRound());
                partido.setResultado(fixture.getScore().getFulltime());

                // Conversión de String a Date
                String dateString = fixture.getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                try {
                    Date date = formatter.parse(dateString.replaceAll("Z$", "+0000"));
                    partido.setFechaInicio(date);  // Establece la fecha convertida
                } catch (ParseException e) {
                    e.printStackTrace();  // Maneja la excepción si ocurre un error de conversión
                }

                partidoRepository.save(partido);
            }
        }
    }
}
