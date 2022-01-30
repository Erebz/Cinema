package com.polytech.cinema.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.polytech.cinema.validations.ValidAjoutActeur;
import com.polytech.cinema.validations.ValidAjoutFilm;
import com.polytech.cinema.validations.ValidModifActeur;
import com.polytech.cinema.validations.ValidModifFilm;
import com.polytech.cinema.validations.ValidSuppressionActeur;
import com.polytech.cinema.validations.ValidSuppressionFilm;

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
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "acteur", schema = "cinema", catalog = "")
public class ActeurEntity {
    private Integer noAct;
    private String nomAct;
    private String prenAct;
    private Date dateNaiss;
    private Date dateDeces;
    private String image;
    private Collection<PersonnageEntity> personnages;

    @Id
    @Column(name = "NoAct")
    @Null(groups = ValidAjoutActeur.class)
    @NotNull(groups = {ValidSuppressionActeur.class, ValidModifActeur.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNoAct() {
        return noAct;
    }
    public void setNoAct(Integer noAct) {
        this.noAct = noAct;
    }

    @Basic
    @Column(name = "NomAct")
    @NotNull(groups = {ValidAjoutActeur.class, ValidModifActeur.class})
    public String getNomAct() { return nomAct; }
    public void setNomAct(String nomAct) { this.nomAct = nomAct; }

    @Basic
    @Column(name = "PrenAct")
    @NotNull(groups = {ValidAjoutActeur.class, ValidModifActeur.class})
    public String getPrenAct() { return prenAct; }
    public void setPrenAct(String prenAct) { this.prenAct = prenAct; }

    @Basic
    @Column(name = "DateNaiss")
    @NotNull(groups = {ValidAjoutActeur.class, ValidModifActeur.class})
    public Date getDateNaiss() { return dateNaiss; }
    public void setDateNaiss(Date dateNaiss) { this.dateNaiss = dateNaiss; }

    @Basic
    @Column(name = "DateDeces")
    public Date getDateDeces() { return dateDeces; }
    public void setDateDeces(Date dateDeces) { this.dateDeces = dateDeces; }

    @Basic
    @Column(name = "image")
    @NotNull(groups = {ValidAjoutActeur.class, ValidModifActeur.class})
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActeurEntity that = (ActeurEntity) o;
        return noAct == that.noAct && Objects.equals(nomAct, that.nomAct) && Objects.equals(prenAct, that.prenAct) && Objects.equals(dateNaiss, that.dateNaiss) && Objects.equals(dateDeces, that.dateDeces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noAct, nomAct, prenAct, dateNaiss, dateDeces);
    }

    @OneToMany(mappedBy = "acteur", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("acteur-perso")
    @Null(groups = {ValidAjoutActeur.class, ValidModifActeur.class, ValidSuppressionActeur.class})
    public Collection<PersonnageEntity> getPersonnages() { return personnages; }
    public void setPersonnages(Collection<PersonnageEntity> personnages) { this.personnages = personnages; }
}
