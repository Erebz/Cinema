package com.polytech.cinema.repositories;

import com.polytech.cinema.domains.ActeurEntity;
import com.polytech.cinema.domains.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CategorieRepository extends JpaRepository<CategorieEntity, Integer> {
    public Optional<CategorieEntity> findByCodeCat(String codeCat);
}
