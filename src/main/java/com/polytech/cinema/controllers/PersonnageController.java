package com.polytech.cinema.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/personnages")
public class PersonnageController {

}

/***
 *   TODO : [Yacine] > Je peux faire ce contrôleur stv
 * - GET - /personnages/list
 * - GET - /personnages/{NoPerso}
 * - GET - /personnages/{NoPerso}/film/{NoFilm}
 * - GET - /personnages/{NoPerso}/acteur/{NoAct}
 * - POST - /personnages/ajouter
 * - POST - /personnages/supprimer
 * - POST - /personnages/modifier
 *
 */