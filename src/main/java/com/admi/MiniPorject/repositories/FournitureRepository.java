package com.admi.MiniPorject.repositories;

import com.admi.MiniPorject.models.Fourniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FournitureRepository extends JpaRepository<Fourniture, Long> {
    @Query("SELECT F FROM Fourniture F WHERE F.fournitureId = ?1")
    Fourniture getFournitureBy(Long id);
}