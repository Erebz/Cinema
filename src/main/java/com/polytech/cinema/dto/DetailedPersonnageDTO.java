package com.polytech.cinema.dto;

import com.polytech.cinema.domains.PersonnageEntity;

public class DetailedPersonnageDTO extends SimplePersonnageDTO{
    private SimpleFilmDTO film;
    private SimpleActeurDTO acteur;

    public SimpleFilmDTO getFilm() { return film; }
    public SimpleActeurDTO getActeur() { return acteur; }

    public DetailedPersonnageDTO(PersonnageEntity perso) {
        super(perso);
        film = new SimpleFilmDTO(perso.getFilm());
        acteur = new SimpleActeurDTO(perso.getActeur());
    }
}
