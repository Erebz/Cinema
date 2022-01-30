package com.polytech.cinema.dto;

import com.polytech.cinema.domains.RealisateurEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class DetailedRealisateurDTO extends SimpleRealisateurDTO{
    private Collection<SimpleFilmDTO> films;

    public Collection<SimpleFilmDTO> getFilms() { return films; }

    public DetailedRealisateurDTO(RealisateurEntity realisateur) {
        super(realisateur);
        films = realisateur.getFilms().stream().map(SimpleFilmDTO::new).collect(Collectors.toList());
    }
}
