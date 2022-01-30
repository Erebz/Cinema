package com.polytech.cinema.services;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ActeurService {
    private final ActeurRepository acteurRepository;
    private final PersonnageRepository personnageRepository;

    @Autowired
    public ActeurService(ActeurRepository acteurRepository, PersonnageRepository personnageRepository) {
        this.acteurRepository = acteurRepository;
        this.personnageRepository = personnageRepository;
    }

    public List<ActeurEntity> getAllActeurs(){
        return acteurRepository.findAll();
    }

    public Optional<ActeurEntity> getbyId(int noActeur) {
        return Optional.ofNullable(acteurRepository.findByNoAct(noActeur));
    }

    @Modifying
    @Transactional
    public void ajouterActeur(ActeurEntity acteur) {
        acteurRepository.save(acteur);
    }

    @Modifying
    @Transactional
    public void supprimerActeur(ActeurEntity acteur) {
        // Supprimer les personnages
        if(acteur.getPersonnages()!=null){
            for(PersonnageEntity personnageEntity : acteur.getPersonnages()){
                personnageRepository.delete(personnageEntity);
            }
        }

        // Supprimer l'acteur
        acteurRepository.delete(acteur);
    }

    public boolean modifierActeur(ActeurEntity acteur) {
        Optional<ActeurEntity> _f = getbyId(acteur.getNoAct());
        if(_f.isEmpty()) return false;
        acteurRepository.save(acteur);
        return true;
    }


    // TODO : les fonctions pour ActeurController
}
