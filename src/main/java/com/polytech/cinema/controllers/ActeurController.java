package com.polytech.cinema.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/acteurs")
public class ActeurController {

}

/**
 * - GET - /acteurs/list
 * - GET - /acteurs/{NoAct}
 * - GET - /acteurs/{NoAct}/films
 * - GET - /acteurs/{NoAct}/personnages
 * - POST - /acteurs/ajouter
 * - POST - /acteurs/supprimer
 * - POST - /acteurs/modifier
 */