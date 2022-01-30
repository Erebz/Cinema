package com.polytech.cinema.dto;

import com.polytech.cinema.domains.CategorieEntity;

import java.io.Serializable;

public class SimpleCategorieDTO implements Serializable {
    private String codeCat;
    private String libelleCat;

    public String getCodeCat() { return codeCat; }
    public String getLibelleCat() { return libelleCat; }

    public SimpleCategorieDTO(CategorieEntity categorie) {
        codeCat = categorie.getCodeCat();
        libelleCat = categorie.getLibelleCat();
    }
}
