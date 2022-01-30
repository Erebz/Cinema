package com.polytech.cinema.services;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.repositories.FilmRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonnageService {
    private final PersonnageRepository personnageRepository;
    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;

    @Autowired
    public PersonnageService(PersonnageRepository PersonnageRepository, FilmRepository FilmRepository, ActeurRepository ActeurRepository) {
        this.personnageRepository = PersonnageRepository;
        this.filmRepository = FilmRepository;
        this.acteurRepository = ActeurRepository;
    }

    public List<PersonnageEntity> getAllPersonnages(){
        return personnageRepository.findAll();
    }

    public Optional<PersonnageEntity> getbyId(int noPerso) {
        return Optional.ofNullable(personnageRepository.findByNoPerso(noPerso));
    }

    public List<ActeurEntity> getActeursOfFilm(FilmEntity film){
        List<PersonnageEntity> persos = personnageRepository.findAllByFilm(film);
        return persos.stream().map(PersonnageEntity::getActeur).collect(Collectors.toList());
    }
}
