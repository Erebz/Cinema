package com.polytech.cinema.controllers;

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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private PersonnageService personnageService;
    @Autowired
    private JwtUserDetailsService jwtService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllFilms(){
        List<FilmEntity> films = filmService.getAllFilms();
        List<DetailedFilmDTO> filmsDTO = films.stream().map(DetailedFilmDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(filmsDTO);
    }

    @GetMapping(value = "/{NoFilm}")
    public ResponseEntity<?> getFilm(@PathVariable("NoFilm") int NoFilm){
        Optional<FilmEntity> film = filmService.getbyId(NoFilm);
        if(film.isPresent()){
            DetailedFilmDTO filmDTO = new DetailedFilmDTO(film.get());
            return ResponseEntity.ok(filmDTO);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{NoFilm}/acteurs")
    public ResponseEntity<?> getActeursOfFilm(@PathVariable("NoFilm") int NoFilm){
        Optional<FilmEntity> film = filmService.getbyId(NoFilm);
        if(film.isPresent()){
            List<ActeurEntity> acteurs = personnageService.getActeursOfFilm(film.get());
            List<DetailedActeurDTO> acteurDTO = acteurs.stream().map(DetailedActeurDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(acteurDTO);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{NoFilm}/realisateur")
    public ResponseEntity<?> getRealisateursOfFilm(@PathVariable("NoFilm") int NoFilm){
        Optional<FilmEntity> film = filmService.getbyId(NoFilm);
        if(film.isPresent()){
            DetailedRealisateurDTO realisateurDTO = new DetailedRealisateurDTO(film.get().getRealisateur());
            return ResponseEntity.ok(realisateurDTO);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{NoFilm}/personnages")
    public ResponseEntity<?> getPersonnagesOfFilm(@PathVariable("NoFilm") int NoFilm){
        Optional<FilmEntity> film = filmService.getbyId(NoFilm);
        if(film.isPresent()){
            Collection<PersonnageEntity> personnages = film.get().getPersonnages();
            List<DetailedPersonnageDTO> personnagesDTO = personnages.stream().map(DetailedPersonnageDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(personnagesDTO);
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/ajouter", consumes = "application/json")
    public ResponseEntity<?> ajouterFilm(@RequestHeader("Authorization") String BearerToken,
                                         @Validated({ValidAjoutFilm.class}) @RequestBody FilmEntity film){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Ajout du film
        filmService.ajouterFilm(film);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/supprimer", consumes = "application/json")
    public ResponseEntity<?> supprimerFilm(@RequestHeader("Authorization") String BearerToken,
                                           @Validated({ValidSuppressionFilm.class}) @RequestBody FilmEntity film){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Suppression du film
        filmService.supprimerFilm(film);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/modifier", consumes = "application/json")
    public ResponseEntity<?> modifierFilm(@RequestHeader("Authorization") String BearerToken,
                                           @Validated({ValidModifFilm.class}) @RequestBody FilmEntity film){
        // Vérification du rôle
        if(!jwtService.checkTokenRole(BearerToken, "admin"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission refusée pour l'utilisateur.");

        // Modification du film
        if(filmService.modifierFilm(film)) return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modification impossible : film introuvable.");
    }

    //TODO GET - /recherche?film=...&real=...&acteur=...
    //Recherche multi-critère avec les infos dans le json du body
    //faire une recherche de type "LIKE" pour trouver les films qui correspondent

}
