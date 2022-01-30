package com.polytech.cinema.repositories;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnageRepository extends JpaRepository<PersonnageEntity, Integer> {
    public PersonnageEntity findByNoPerso(int noPerso);
    public List<PersonnageEntity> findAllByFilm(FilmEntity film);
}
