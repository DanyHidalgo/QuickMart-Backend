package com.ufm.QuickMart.dto;

import com.ufm.QuickMart.entities.Torneo;
import java.util.List;

public class TournamentResponse {
    private List<Torneo> response;

    public List<Torneo> getResponse() {
        return response;
    }

    public void setResponse(List<Torneo> response) {
        this.response = response;
    }
}
