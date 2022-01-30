package com.polytech.cinema.dto;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.PersonnageEntity;

import java.sql.Date;
import java.util.Collection;

public class SimpleActeurDTO {
    private int noAct;
    private String nomAct;
    private String prenAct;
    private Date dateNaiss;
    private Date dateDeces;
    private String image;

    public int getNoAct() { return noAct; }
    public String getNomAct() { return nomAct; }
    public String getPrenAct() { return prenAct; }
    public Date getDateNaiss() { return dateNaiss; }
    public Date getDateDeces() { return dateDeces; }
    public String getImage() { return image; }

    public SimpleActeurDTO(ActeurEntity acteur){
        noAct = acteur.getNoAct();
        nomAct = acteur.getNomAct();
        prenAct = acteur.getPrenAct();
        dateNaiss = acteur.getDateNaiss();
        dateDeces = acteur.getDateDeces();
        image = acteur.getImage();
    }
}
