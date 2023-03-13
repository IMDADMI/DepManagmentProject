package com.admi.MiniPorject.repositories;

import com.admi.MiniPorject.models.Fourniture;
import com.admi.MiniPorject.models.FournitureOrder;
import com.admi.MiniPorject.models.MaterialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FournitureOrderRepository extends JpaRepository<FournitureOrder, Long> {

    @Query("SELECT F FROM Fourniture F where F.fournitureId = ?1")
    Fourniture getFournitureById(Long id);

    @Query("SELECT FO FROM FournitureOrder FO where FO.FournitureOrderId = ?1")
    FournitureOrder getFournitureOrderById(Long id);
}