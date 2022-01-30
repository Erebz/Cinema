package com.polytech.cinema.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.polytech.cinema.validations.ValidAjoutFilm;
import com.polytech.cinema.validations.ValidAjoutRealisateur;
import com.polytech.cinema.validations.ValidModifFilm;
import com.polytech.cinema.validations.ValidModifRealisateur;
import com.polytech.cinema.validations.ValidSuppressionFilm;
import com.polytech.cinema.validations.ValidSuppressionRealisateur;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "realisateur", schema = "cinema", catalog = "")
public class RealisateurEntity {
    private Integer noRea;
    private String nomRea;
    private String prenRea;
    private String image;
    private Collection<FilmEntity> films;

    @Id
    @Column(name = "NoRea")
    @Null(groups = ValidAjoutRealisateur.class)
    @NotNull(groups = {ValidSuppressionRealisateur.class, ValidModifRealisateur.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNoRea() {
        return noRea;
    }
    public void setNoRea(Integer noRea) {
        this.noRea = noRea;
    }

    @Basic
    @Column(name = "NomRea")
    @NotNull(groups = {ValidAjoutRealisateur.class, ValidModifRealisateur.class})
    public String getNomRea() {
        return nomRea;
    }
    public void setNomRea(String nomRea) {
        this.nomRea = nomRea;
    }

    @Basic
    @Column(name = "PrenRea")
    @NotNull(groups = {ValidAjoutRealisateur.class, ValidModifRealisateur.class})
    public String getPrenRea() {
        return prenRea;
    }
    public void setPrenRea(String prenRea) {
        this.prenRea = prenRea;
    }

    @Basic
    @Column(name = "image")
    @NotNull(groups = {ValidAjoutRealisateur.class, ValidModifRealisateur.class})
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

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
    @Null(groups = {ValidSuppressionRealisateur.class, ValidAjoutRealisateur.class, ValidModifRealisateur.class})
    public Collection<FilmEntity> getFilms() {
        return films;
    }
    public void setFilms(Collection<FilmEntity> films) {
        this.films = films;
    }
}
