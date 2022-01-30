package com.polytech.cinema.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "realisateur", schema = "cinema", catalog = "")
public class RealisateurEntity {
    private Integer noRea;
    private String nomRea;
    private String prenRea;
    private Collection<FilmEntity> films;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoRea")
    public Integer getNoRea() {
        return noRea;
    }
    public void setNoRea(Integer noRea) {
        this.noRea = noRea;
    }

    @Basic
    @Column(name = "NomRea")
    public String getNomRea() {
        return nomRea;
    }
    public void setNomRea(String nomRea) {
        this.nomRea = nomRea;
    }

    @Basic
    @Column(name = "PrenRea")
    public String getPrenRea() {
        return prenRea;
    }
    public void setPrenRea(String prenRea) {
        this.prenRea = prenRea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealisateurEntity that = (RealisateurEntity) o;
        return noRea == that.noRea && Objects.equals(nomRea, that.nomRea) && Objects.equals(prenRea, that.prenRea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noRea, nomRea, prenRea);
    }

    @OneToMany(mappedBy = "realisateur", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("realisateur-film")
    public Collection<FilmEntity> getFilms() {
        return films;
    }
    public void setFilms(Collection<FilmEntity> films) {
        this.films = films;
    }
}
