package com.polytech.cinema.controllers;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.repositories.ActeurRepository;
import com.polytech.cinema.services.ActeurService;
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
@RequestMapping("/acteurs")
public class ActeurController {
    @Autowired
    ActeurService acteurService;

    @Autowired
    PersonnageService personnageService;

    @Autowired
    private JwtUserDetailsService jwtService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllActeurs(){ return ResponseEntity.ok(acteurService.getAllActeurs()); }

    @GetMapping("/{noAct}")
    public ResponseEntity<?> getActeurById(@PathVariable int noAct){
        Optional<ActeurEntity> acteur = acteurService.getbyId(noAct);
        if(acteur.isPresent()){
            DetailedActeurDTO acteurDTO = new DetailedActeurDTO(acteur.get());
            return ResponseEntity.ok(acteurDTO);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{noAct}/films")
    public ResponseEntity<?> getFilmForActeur(@PathVariable int noAct){
        Optional<ActeurEntity> acteur = acteurService.getbyId(noAct);
        if(acteur.isPresent()){
            List<FilmEntity> acteurs = personnageService.getFilmsOfActeur(acteur.get());
            List<DetailedFilmDTO> filmDTO = acteurs.stream().map(DetailedFilmDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(filmDTO);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{noAct}/personnages")
    public ResponseEntity<?> getPersonnagesForActeur(@PathVariable int noAct) {
        Optional<ActeurEntity> acteur = acteurService.getbyId(noAct);
        if (acteur.isPresent()) {
            Collection<PersonnageEntity> personnages = acteur.get().getPersonnages();
            List<DetailedPersonnageDTO> acteurDTO = personnages.stream().map(DetailedPersonnageDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(acteurDTO);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/ajouter",consumes = "application/json")
    public ResponseEntity<?> ajouterActeur(@RequestHeader("Authorization") String BearerToken,
                                         @Validated({ValidAjoutActeur.class}) @RequestBody ActeurEntity acteur){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Ajout de l'acteur
        acteurService.ajouterActeur(acteur);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/supprimer",consumes = "application/json")
    public ResponseEntity<?> supprimerActeur(@RequestHeader("Authorization") String BearerToken,
                                           @Validated({ValidSuppressionActeur.class}) @RequestBody ActeurEntity acteur){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Ajout de l'acteur
        acteurService.supprimerActeur(acteur);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/modifier", consumes = "application/json")
    public ResponseEntity<?> modifierActeur(@RequestHeader("Authorization") String BearerToken,
                                          @Validated({ValidModifActeur.class}) @RequestBody ActeurEntity acteur){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Modification de l'acteur
        if(acteurService.modifierActeur(acteur)) return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modification impossible : acteur introuvable.");
    }
}

/**
 * - GET - /acteurs/list (OK)
 * - GET - /acteurs/{NoAct} (OK)
 * - GET - /acteurs/{NoAct}/films (OK)
 * - GET - /acteurs/{NoAct}/personnages (OK)
 * - POST - /acteurs/ajouter (OK)
 * - POST - /acteurs/supprimer (OK)
 * - POST - /acteurs/modifier (OK)
 */