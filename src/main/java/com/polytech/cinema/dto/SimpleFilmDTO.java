package com.polytech.cinema.dto;

import com.polytech.cinema.domains.FilmEntity;

import java.io.Serializable;
import java.sql.Date;

public class SimpleFilmDTO implements Serializable {
    protected int noFilm;
    protected String titre;
    protected String image;
    protected int duree;
    protected Date dateSortie;
    protected int budget;
    protected int montantRecette;

    public int getNoFilm() { return noFilm; }
    public String getTitre() { return titre; }
    public String getImage() { return image; }
    public int getDuree() { return duree; }
    public Date getDateSortie() { return dateSortie; }
    public int getBudget() { return budget; }
    public int getMontantRecette() { return montantRecette; }

    public SimpleFilmDTO(FilmEntity film){
        noFilm = film.getNoFilm();
        titre = film.getTitre();
        image = film.getImage();
        duree = film.getDuree();
        dateSortie = film.getDateSortie();
        budget = film.getBudget();
        montantRecette = film.getMontantRecette();
    }
}
