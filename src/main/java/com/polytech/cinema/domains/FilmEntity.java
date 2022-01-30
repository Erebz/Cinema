package com.polytech.cinema.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.polytech.cinema.validations.ValidAjoutFilm;
import com.polytech.cinema.validations.ValidModifFilm;
import com.polytech.cinema.validations.ValidSuppressionFilm;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "cinema", catalog = "")
public class FilmEntity {
    private Integer noFilm;
    private String titre;
    private String image;
    private int duree;
    private Date dateSortie;
    private int budget;
    private int montantRecette;
    private RealisateurEntity realisateur;
    private CategorieEntity categorie;
    private Collection<PersonnageEntity> personnages;

    @Id
    @Column(name = "NoFilm")
    @Null(groups = ValidAjoutFilm.class)
    @NotNull(groups = {ValidSuppressionFilm.class, ValidModifFilm.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNoFilm() { return noFilm; }
    public void setNoFilm(Integer noFilm) { this.noFilm = noFilm; }

    @Basic
    @Column(name = "Titre")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Basic
    @Column(name = "image")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "Duree")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Basic
    @Column(name = "DateSortie")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    public Date getDateSortie() {
        return dateSortie;
    }
    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    @Basic
    @Column(name = "Budget")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    public int getBudget() {
        return budget;
    }
    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "MontantRecette")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    public int getMontantRecette() {
        return montantRecette;
    }
    public void setMontantRecette(int montantRecette) {
        this.montantRecette = montantRecette;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return noFilm == that.noFilm && duree == that.duree && budget == that.budget && montantRecette == that.montantRecette && Objects.equals(titre, that.titre) && Objects.equals(image, that.image) && Objects.equals(dateSortie, that.dateSortie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noFilm, titre, image, duree, dateSortie, budget, montantRecette);
    }

    @ManyToOne
    @JoinColumn(name = "NoRea", referencedColumnName = "NoRea")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    @JsonBackReference("realisateur-film")
    public RealisateurEntity getRealisateur() {
        return realisateur;
    }
    public void setRealisateur(RealisateurEntity realisateur) {
        this.realisateur = realisateur;
    }

    @ManyToOne
    @JoinColumn(name = "CodeCat", referencedColumnName = "CodeCat")
    @NotNull(groups = {ValidAjoutFilm.class, ValidModifFilm.class})
    @JsonBackReference("categorie-film")
    public CategorieEntity getCategorie() {
        return categorie;
    }
    public void setCategorie(CategorieEntity categorie) {
        this.categorie = categorie;
    }

    @OneToMany(mappedBy = "film", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("film-perso")
    @Null(groups = {ValidAjoutFilm.class, ValidModifFilm.class, ValidSuppressionFilm.class})
    public Collection<PersonnageEntity> getPersonnages() {
        return personnages;
    }
    public void setPersonnages(Collection<PersonnageEntity> personnages) {
        this.personnages = personnages;
    }
}
