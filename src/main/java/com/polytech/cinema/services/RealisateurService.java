package com.polytech.cinema.services;
import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.domains.RealisateurEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.repositories.PersonnageRepository;
import com.polytech.cinema.repositories.RealisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RealisateurService {
    private final RealisateurRepository realisateurRepository;

    @Autowired
    public RealisateurService(RealisateurRepository realisateurRepository){
        this.realisateurRepository = realisateurRepository;
    }

    public List<RealisateurEntity> getAllRealisateurs(){
        return realisateurRepository.findAll();
    }

    public Optional<RealisateurEntity> getRealisateurById(int id){
        return Optional.ofNullable(realisateurRepository.findByNoRea(id));
    }

    @Modifying
    @Transactional
    public void ajouterRealisateur(RealisateurEntity real) {
        realisateurRepository.save(real);
    }

    @Modifying
    @Transactional
    public boolean supprimerRealisateur(RealisateurEntity real) {
        // Si le real a des films, on ne supprime pas
        if(real.getFilms()!=null && !real.getFilms().isEmpty()){
            return false;
        }
        realisateurRepository.delete(real);
        return true;
    }

    @Modifying
    @Transactional
    public boolean modifierRealisateur(RealisateurEntity real) {
        Optional<RealisateurEntity> _f = getRealisateurById(real.getNoRea());
        if(_f.isEmpty()) return false;
        realisateurRepository.save(real);
        return true;
    }
}
