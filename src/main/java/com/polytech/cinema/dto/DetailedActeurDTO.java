package com.polytech.cinema.dto;

import com.polytech.cinema.domains.ActeurEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class DetailedActeurDTO extends SimpleActeurDTO{
    private Collection<SimplePersonnageDTO> personnages;

    public Collection<SimplePersonnageDTO> getPersonnages() { return personnages; }

    public DetailedActeurDTO(ActeurEntity acteur) {
        super(acteur);
        personnages = acteur.getPersonnages().stream().map(SimplePersonnageDTO::new).collect(Collectors.toList());
    }
}
