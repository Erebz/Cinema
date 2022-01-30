package com.polytech.cinema.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categorie", schema = "cinema", catalog = "")
public class CategorieEntity {
    private String codeCat;
    private String libelleCat;
    private Collection<FilmEntity> films;

    @Id
    @Column(name = "CodeCat")
    public String getCodeCat() {
        return codeCat;
    }
    public void setCodeCat(String codeCat) {
        this.codeCat = codeCat;
    }

    @Basic
    @Column(name = "LibelleCat")
    public String getLibelleCat() {
        return libelleCat;
    }
    public void setLibelleCat(String libelleCat) {
        this.libelleCat = libelleCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorieEntity that = (CategorieEntity) o;
        return Objects.equals(codeCat, that.codeCat) && Objects.equals(libelleCat, that.libelleCat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeCat, libelleCat);
    }

    @OneToMany(mappedBy = "categorie", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("categorie-film")
    public Collection<FilmEntity> getFilms() {
        return films;
    }
    public void setFilms(Collection<FilmEntity> films) {
        this.films = films;
    }
}
