package com.polytech.cinema.services;

import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActeurService {
    private final ActeurRepository acteurRepository;
    private final PersonnageRepository personnageRepository;

    @Autowired
    public ActeurService(ActeurRepository acteurRepository, PersonnageRepository personnageRepository) {
        this.acteurRepository = acteurRepository;
        this.personnageRepository = personnageRepository;
    }

    // TODO : les fonctions pour ActeurController
}
