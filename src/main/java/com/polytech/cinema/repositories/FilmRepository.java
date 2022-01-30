package com.polytech.cinema.repositories;

import com.polytech.cinema.domains.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {
    public FilmEntity findByNoFilm(int NoFilm);
}
