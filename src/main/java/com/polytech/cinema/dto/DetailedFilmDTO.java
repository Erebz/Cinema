package com.polytech.cinema.dto;

import com.polytech.cinema.domains.FilmEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class DetailedFilmDTO extends SimpleFilmDTO {
    private SimpleRealisateurDTO realisateur;
    private SimpleCategorieDTO categorie;
    private Collection<SimplePersonnageDTO> personnages;

    public SimpleRealisateurDTO getRealisateur() { return realisateur; }
    public SimpleCategorieDTO getCategorie() { return categorie; }
    public Collection<SimplePersonnageDTO> getPersonnages() { return personnages; }

    public DetailedFilmDTO(FilmEntity film){
        super(film);
        realisateur = new SimpleRealisateurDTO(film.getRealisateur());
        categorie = new SimpleCategorieDTO(film.getCategorie());
        personnages = film.getPersonnages().stream().map(SimplePersonnageDTO::new).collect(Collectors.toList());
    }
}
