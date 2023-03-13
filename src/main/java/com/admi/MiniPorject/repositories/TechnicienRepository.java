package com.admi.MiniPorject.repositories;

import com.admi.MiniPorject.models.Person;
import com.admi.MiniPorject.models.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnicienRepository extends JpaRepository<Person,Long> {
    @Query("SELECT P FROM Person P WHERE P.type = ?1")
    List<Person> getTechniciens(Type technicien);

    @Query("SELECT P FROM Person P WHERE P.personId = ?1")
    Person getTechnicien(Long technicienId);

    @Query("SELECT P FROM Person P WHERE P.cin = ?1")
    Person getTechnicienByCin(String cin);




}
