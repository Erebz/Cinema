package com.polytech.cinema.services;

import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.repositories.FilmRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final PersonnageRepository personnageRepository;

    @Autowired
    public FilmService(FilmRepository FilmRepository, PersonnageRepository PersonnageRepository) {
        this.filmRepository = FilmRepository;
        this.personnageRepository = PersonnageRepository;
    }

    public List<FilmEntity> getAllFilms(){
       return filmRepository.findAll();
    }

    public Optional<FilmEntity> getbyId(int noFilm) {
        return Optional.ofNullable(filmRepository.findByNoFilm(noFilm));
    }

    @Modifying
    @Transactional
    public void ajouterFilm(FilmEntity film) { filmRepository.save(film); }

    @Modifying
    @Transactional
    public void supprimerFilm(FilmEntity film) {
        // Supprimer les personnages
        if(film.getPersonnages() != null)
            for (PersonnageEntity personnageEntity : film.getPersonnages()) {
                personnageRepository.delete(personnageEntity);
            }

        // Supprimer le film
        filmRepository.delete(film);
    }

    @Modifying
    @Transactional
    public boolean modifierFilm(FilmEntity film) {
        Optional<FilmEntity> _f = getbyId(film.getNoFilm());
        if(_f.isEmpty()) return false;
        filmRepository.save(film);
        return true;
    }
}
