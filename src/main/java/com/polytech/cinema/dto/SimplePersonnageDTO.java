package com.polytech.cinema.dto;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;

public class SimplePersonnageDTO {
    private int noPerso;
    private String nomPers;

    public int getNoPerso() { return noPerso; }
    public String getNomPers() { return nomPers; }

    public SimplePersonnageDTO(PersonnageEntity perso){
        noPerso = perso.getNoPerso();
        nomPers = perso.getNomPers();
    }
}
