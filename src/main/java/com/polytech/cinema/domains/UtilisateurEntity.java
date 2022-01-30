package com.polytech.cinema.domains;

import com.polytech.cinema.validations.ValidAjoutUtilisateur;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Entity
@Table(name = "utilisateur", schema = "cinema", catalog = "")
public class UtilisateurEntity {
    private Integer noUtil;
    private String nomUtil;
    private String motPasse;
    private String role;

    @Id
    @Column(name = "NoUtil")
    @Null(groups = ValidAjoutUtilisateur.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getNoUtil() {
        return noUtil;
    }
    public void setNoUtil(Integer noUtil) {
        this.noUtil = noUtil;
    }

    @Basic
    @Column(name = "NomUtil")
    @NotNull(groups = ValidAjoutUtilisateur.class)
    public String getNomUtil() {
        return nomUtil;
    }
    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    @Basic
    @Column(name = "MotPasse")
    @NotNull(groups = ValidAjoutUtilisateur.class)
    public String getMotPasse() {
        return motPasse;
    }
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    @Basic
    @Column(name = "Role")
    @Null(groups = ValidAjoutUtilisateur.class)
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurEntity that = (UtilisateurEntity) o;
        return noUtil == that.noUtil && Objects.equals(nomUtil, that.nomUtil) && Objects.equals(motPasse, that.motPasse) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noUtil, nomUtil, motPasse, role);
    }
}
