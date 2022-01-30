package com.polytech.cinema.repositories;

import com.polytech.cinema.domains.ActeurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActeurRepository extends JpaRepository<ActeurEntity, Integer> {
    public ActeurEntity findByNoAct(int NoAct);
}
