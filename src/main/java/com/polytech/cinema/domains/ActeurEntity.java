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
    private Collection<PersonnageEntity> personnages;

    @Id
    @Column(name = "NoAct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNoAct() {
        return noAct;
    }
    public void setNoAct(Integer noAct) {
        this.noAct = noAct;
    }

    @Basic
    @Column(name = "NomAct")
    public String getNomAct() {
        return nomAct;
    }
    public void setNomAct(String nomAct) {
        this.nomAct = nomAct;
    }

    @Basic
    @Column(name = "PrenAct")
    public String getPrenAct() {
        return prenAct;
    }
    public void setPrenAct(String prenAct) {
        this.prenAct = prenAct;
    }

    @Basic
    @Column(name = "DateNaiss")
    public Date getDateNaiss() {
        return dateNaiss;
    }
    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    @Basic
    @Column(name = "DateDeces")
    public Date getDateDeces() {
        return dateDeces;
    }
    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }

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
    public Collection<PersonnageEntity> getPersonnages() {
        return personnages;
    }
    public void setPersonnages(Collection<PersonnageEntity> personnages) {
        this.personnages = personnages;
    }
}
