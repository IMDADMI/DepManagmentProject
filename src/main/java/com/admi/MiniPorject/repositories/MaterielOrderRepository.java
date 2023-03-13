package com.admi.MiniPorject.repositories;

import com.admi.MiniPorject.models.Fourniture;
import com.admi.MiniPorject.models.Material;
import com.admi.MiniPorject.models.MaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterielOrderRepository extends JpaRepository<MaterialOrder, Long> {
    @Query("SELECT M FROM Material M where M.numeroInventaire = ?1")
    Material getMaterialById(Long id);

    @Query("SELECT MO FROM MaterialOrder MO where MO.materielOrderId = ?1")
    MaterialOrder getMaterialOrderById(Long id);

}