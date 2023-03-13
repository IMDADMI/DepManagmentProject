package com.admi.MiniPorject.repositories;

import com.admi.MiniPorject.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterielRepository extends JpaRepository<Material, Long> {
    @Query("SELECT M FROM Material M WHERE M.numeroInventaire = ?1")
    Material getMaterielById(Long id);
}