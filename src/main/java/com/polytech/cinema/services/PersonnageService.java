package com.polytech.cinema.services;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.repositories.FilmRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<FilmEntity> getFilmsOfActeur(ActeurEntity acteur){
        List<PersonnageEntity> persos = personnageRepository.findAllByActeur(acteur);
        return persos.stream().map(PersonnageEntity::getFilm).collect(Collectors.toList());
    }

    @Modifying
    @Transactional
    public boolean ajouterPersonnage(PersonnageEntity perso) {
        return savePersonnage(perso);
    }

    @Modifying
    @Transactional
    public boolean modifierPersonnage(PersonnageEntity perso) {
        Optional<PersonnageEntity> _f = getbyId(perso.getNoPerso());
        if(_f.isEmpty()) return false;
        return savePersonnage(perso);
    }

    private boolean savePersonnage(PersonnageEntity perso){
        ActeurEntity acteur = acteurRepository.findByNoAct(perso.getActeur().getNoAct());
        FilmEntity film = filmRepository.findByNoFilm(perso.getFilm().getNoFilm());
        if(acteur==null || film==null) return false;
        perso.setActeur(acteur);
        perso.setFilm(film);
        personnageRepository.save(perso);
        return true;
    }

    @Modifying
    @Transactional
    public void supprimerPersonnage(PersonnageEntity perso) { personnageRepository.delete(perso); }
}
