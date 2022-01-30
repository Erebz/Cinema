package com.polytech.cinema.controllers;

import com.polytech.cinema.domains.RealisateurEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.services.RealisateurService;
import com.polytech.cinema.validations.ValidModifActeur;
import com.polytech.cinema.validations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.validations.ValidAjoutFilm;
import com.polytech.cinema.validations.ValidModifFilm;
import com.polytech.cinema.validations.ValidSuppressionFilm;
import com.polytech.cinema.dto.DetailedActeurDTO;
import com.polytech.cinema.dto.DetailedFilmDTO;
import com.polytech.cinema.dto.DetailedPersonnageDTO;
import com.polytech.cinema.dto.DetailedRealisateurDTO;
import com.polytech.cinema.services.FilmService;
import com.polytech.cinema.services.JwtUserDetailsService;
import com.polytech.cinema.services.PersonnageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/realisateurs")
public class RealisateurController {
    @Autowired
    RealisateurService realisateurService;

    @Autowired
    FilmService filmService;

    @Autowired
    private JwtUserDetailsService jwtService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllRealisateurs(){
        return ResponseEntity.ok(realisateurService.getAllRealisateurs());
    }

    @GetMapping("/{noRea}")
    public ResponseEntity<?> getRealisateurById(@PathVariable int noRea){
        Optional<RealisateurEntity> real = realisateurService.getRealisateurById(noRea);
        if(real.isPresent()){
            DetailedRealisateurDTO realisateurDTO = new DetailedRealisateurDTO(real.get());
            return ResponseEntity.ok(realisateurDTO);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{noRea}/films")
    public ResponseEntity<?> getFilmsForRealisateur(@PathVariable int noRea){
        Optional<RealisateurEntity> real = realisateurService.getRealisateurById(noRea);
        if(real.isPresent()){
            Collection<FilmEntity> films = real.get().getFilms();
            List<DetailedFilmDTO> filmsDTO = films.stream().map(DetailedFilmDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(filmsDTO);
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/ajouter",consumes = "application/json")
    public ResponseEntity<?> ajouterActeur(@RequestHeader("Authorization") String BearerToken,
                                           @Validated({ValidAjoutRealisateur.class}) @RequestBody RealisateurEntity real){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Ajout de l'acteur
        realisateurService.ajouterRealisateur(real);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/supprimer",consumes = "application/json")
    public ResponseEntity<?> supprimerActeur(@RequestHeader("Authorization") String BearerToken,
                                             @Validated({ValidSuppressionRealisateur.class}) @RequestBody RealisateurEntity real){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Suppression du real, si false (pas delete car films en bd) on envoie un message
        if(!realisateurService.supprimerRealisateur(real)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Impossible de supprimer le realisateur car il est lié à des films.");
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/modifier", consumes = "application/json")
    public ResponseEntity<?> modifierActeur(@RequestHeader("Authorization") String BearerToken,
                                            @Validated({ValidModifRealisateur.class}) @RequestBody RealisateurEntity real){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Modification de l'acteur
        if(realisateurService.modifierRealisateur(real)) return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modification impossible : realisateur introuvable.");
    }
}

/**
 - GET - /realisateurs/list (OK)
 - GET - /realisateurs/{NoRea} (OK)
 - GET - /realisateurs/{NoRea}/films (OK)
 - POST - /realisateurs/ajouter (OK)
 - POST - /realisateurs/supprimer (OK)
 - POST - /realisateurs/modifier (OK)
 */