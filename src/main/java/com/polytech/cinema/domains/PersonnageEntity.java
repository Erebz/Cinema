package com.polytech.cinema.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "personnage", schema = "cinema", catalog = "")
public class PersonnageEntity {
    private Integer noPerso;
    private String nomPers;
    private FilmEntity film;
    private ActeurEntity acteur;

    @Id
    @Column(name = "NoPerso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNoPerso() {
        return noPerso;
    }
    public void setNoPerso(Integer noPerso) {
        this.noPerso = noPerso;
    }

    @Basic
    @Column(name = "NomPers")
    public String getNomPers() {
        return nomPers;
    }
    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageEntity that = (PersonnageEntity) o;
        return noPerso == that.noPerso && Objects.equals(nomPers, that.nomPers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noPerso, nomPers);
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "NoFilm", referencedColumnName = "NoFilm", nullable = false)
    @JsonBackReference("film-perso")
    public FilmEntity getFilm() {
        return film;
    }
    public void setFilm(FilmEntity film) {
        this.film = film;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "NoAct", referencedColumnName = "NoAct")
    @JsonBackReference("acteur-perso")
    public ActeurEntity getActeur() {
        return acteur;
    }
    public void setActeur(ActeurEntity acteur) {
        this.acteur = acteur;
    }
}
