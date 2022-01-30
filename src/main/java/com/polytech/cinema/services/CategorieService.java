package com.polytech.cinema.services;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.CategorieEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.domains.RealisateurEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.repositories.CategorieRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<CategorieEntity> getAllCategories(){
        return categorieRepository.findAll();
    }

    public Optional<CategorieEntity> getByCodeCat(String codeCat){
        return categorieRepository.findByCodeCat(codeCat);
    }

    @Modifying
    @Transactional
    public void ajouterCategorie(CategorieEntity categorie) {
        categorieRepository.save(categorie);
    }

    @Modifying
    @Transactional
    public boolean supprimerCategorie(CategorieEntity categorie) {
        // Si le real a des films, on ne supprime pas
        if(!categorie.getFilms().isEmpty()){
            return false;
        }
        categorieRepository.delete(categorie);
        return true;
    }

    public boolean modifierCategorie(CategorieEntity categorie) {
        Optional<CategorieEntity> _f = getByCodeCat(categorie.getCodeCat());
        if(_f.isEmpty()) return false;
        categorieRepository.save(categorie);
        return true;
    }


}
