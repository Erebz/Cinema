package com.polytech.cinema.dto;


import com.polytech.cinema.domains.CategorieEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class DetailedCategorieDTO extends SimpleCategorieDTO {
    private Collection<SimpleFilmDTO> films;

    public Collection<SimpleFilmDTO> getFilms() { return films; }

    public DetailedCategorieDTO(CategorieEntity categorie) {
        super(categorie);
        films = categorie.getFilms().stream().map(SimpleFilmDTO::new).collect(Collectors.toList());
    }
}
