package com.ufm.QuickMart.dto;

import java.util.List;

public class PartidoResponse {

    private List<Fixture> response;

    // Getters y Setters
    public List<Fixture> getResponse() {
        return response;
    }

    public void setResponse(List<Fixture> response) {
        this.response = response;
    }

    public static class Fixture {
        private Long id;
        private String round;
        private Score score;
        private String date;

        // Getters y Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        public Score getScore() {
            return score;
        }

        public void setScore(Score score) {
            this.score = score;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class Score {
        private String fulltime;

        // Getters y Setters
        public String getFulltime() {
            return fulltime;
        }

        public void setFulltime(String fulltime) {
            this.fulltime = fulltime;
        }
    }
}
