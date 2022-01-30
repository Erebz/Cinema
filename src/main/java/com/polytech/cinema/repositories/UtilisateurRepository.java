package com.polytech.cinema.repositories;

import com.polytech.cinema.domains.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {

    @Query("select ut from UtilisateurEntity ut where ut.nomUtil = ?1")
    UtilisateurEntity rechercheNom(String username);

}
