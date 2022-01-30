package com.polytech.cinema.controllers;

import com.polytech.cinema.domains.PersonnageEntity;
import com.polytech.cinema.dto.DetailedPersonnageDTO;
import com.polytech.cinema.services.JwtUserDetailsService;
import com.polytech.cinema.services.PersonnageService;
import com.polytech.cinema.validations.ValidAjoutPerso;
import com.polytech.cinema.validations.ValidModifPerso;
import com.polytech.cinema.validations.ValidSuppressionPerso;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/personnages")
public class PersonnageController {
    @Autowired
    PersonnageService personnageService;
    @Autowired
    private JwtUserDetailsService jwtService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllPersonnages(){
        List<PersonnageEntity> perso = personnageService.getAllPersonnages();
        List<DetailedPersonnageDTO>  persoDTO = perso.stream().map(DetailedPersonnageDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(persoDTO);
    }

    @GetMapping("/{NoPerso}")
    public ResponseEntity<?> getPersonnage(@PathVariable("NoPerso") int noPerso){
        Optional<PersonnageEntity> perso = personnageService.getbyId(noPerso);
        if(perso.isPresent()){
            DetailedPersonnageDTO persoDTO = new DetailedPersonnageDTO(perso.get());
            return ResponseEntity.ok(persoDTO);
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/ajouter", consumes = "application/json")
    public ResponseEntity<?> ajouterPersonnage(@RequestHeader("Authorization") String BearerToken,
                                         @Validated({ValidAjoutPerso.class}) @RequestBody PersonnageEntity perso){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Ajout du perso
        if(personnageService.ajouterPersonnage(perso)) return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ajout impossible : film ou acteur introuvables.");
    }

    @PostMapping(value = "/modifier", consumes = "application/json")
    public ResponseEntity<?> modifierPersonnage(@RequestHeader("Authorization") String BearerToken,
                                                @Validated({ValidModifPerso.class}) @RequestBody PersonnageEntity perso){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Modification du perso
        if(personnageService.modifierPersonnage(perso)) return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modification impossible : personnage, film ou acteur introuvables.");
    }

    @PostMapping(value = "/supprimer", consumes = "application/json")
    public ResponseEntity<?> supprimerPersonnage(@RequestHeader("Authorization") String BearerToken,
                                                @Validated({ValidSuppressionPerso.class}) @RequestBody PersonnageEntity perso){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Suppression du perso
        personnageService.supprimerPersonnage(perso);
        return ResponseEntity.ok().build();
    }

}

/***
 *   TODO : [Yacine] > Je peux faire ce contrôleur stv
 * - GET - (?) /personnages/{NoPerso}/film/{NoFilm}
 * - GET - (?) /personnages/{NoPerso}/acteur/{NoAct}
 * - POST - /personnages/ajouter ✔
 * - POST - /personnages/supprimer ✔
 * - POST - /personnages/modifier ✔
 *
 */