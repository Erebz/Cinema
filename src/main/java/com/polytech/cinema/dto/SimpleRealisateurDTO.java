package com.polytech.cinema.dto;

import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.RealisateurEntity;

import java.util.Collection;

public class SimpleRealisateurDTO {
    private int noRea;
    private String nomRea;
    private String prenRea;

    public int getNoRea() { return noRea; }
    public String getNomRea() { return nomRea; }
    public String getPrenRea() { return prenRea; }

    public SimpleRealisateurDTO(RealisateurEntity realisateur) {
        noRea = realisateur.getNoRea();
        nomRea = realisateur.getNomRea();
        prenRea = realisateur.getPrenRea();
    }
}
