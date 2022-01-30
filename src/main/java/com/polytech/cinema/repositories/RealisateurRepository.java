package com.polytech.cinema.repositories;
import com.polytech.cinema.domains.RealisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealisateurRepository extends JpaRepository<RealisateurEntity, Integer>{
    public RealisateurEntity findByNoRea(int noRea);
}
