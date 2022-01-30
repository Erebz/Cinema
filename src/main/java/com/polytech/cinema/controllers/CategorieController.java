package com.polytech.cinema.controllers;

import com.polytech.cinema.domains.CategorieEntity;
import com.polytech.cinema.domains.FilmEntity;
import com.polytech.cinema.dto.DetailedCategorieDTO;
import com.polytech.cinema.dto.DetailedFilmDTO;
import com.polytech.cinema.dto.SimpleCategorieDTO;
import com.polytech.cinema.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    CategorieService categorieService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllCategories(){
        List<CategorieEntity> categories = categorieService.getAllCategories();
        List<SimpleCategorieDTO> categorieDTO = categories.stream().map(SimpleCategorieDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(categorieDTO);
    }

    @GetMapping("/{CodeCat}")
    public ResponseEntity<?> getCategorie(@PathVariable("CodeCat") String codeCat){
        Optional<CategorieEntity> categorie = categorieService.getByCodeCat(codeCat);
        if(categorie.isPresent()){
            DetailedCategorieDTO categorieDTO = new DetailedCategorieDTO(categorie.get());
            return ResponseEntity.ok(categorieDTO);
        }else return ResponseEntity.notFound().build();
    }

    @GetMapping("/{CodeCat}/films")
    public ResponseEntity<?> getFilmsOfCategorie(@PathVariable("CodeCat") String codeCat){
        Optional<CategorieEntity> categorie = categorieService.getByCodeCat(codeCat);
        if(categorie.isPresent()){
            Collection<FilmEntity> films = categorie.get().getFilms();
            List<DetailedFilmDTO> filmsDTO = films.stream().map(DetailedFilmDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(filmsDTO);
        }else return ResponseEntity.notFound().build();
    }

}
