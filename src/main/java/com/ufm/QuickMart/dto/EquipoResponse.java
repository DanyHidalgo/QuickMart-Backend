package com.ufm.QuickMart.dto;

import java.util.List;

public class EquipoResponse {

    private List<Team> response;

    // Getters y Setters
    public List<Team> getResponse() {
        return response;
    }

    public void setResponse(List<Team> response) {
        this.response = response;
    }

    public static class Team {
        private Long id;
        private String name;

        // Getters y Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
