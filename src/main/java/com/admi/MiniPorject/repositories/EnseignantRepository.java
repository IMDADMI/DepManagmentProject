package com.admi.MiniPorject.repositories;

import com.admi.MiniPorject.models.Person;
import com.admi.MiniPorject.models.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Person,Long> {

    @Query("SELECT P FROM Person P WHERE P.type = ?1")
    List<Person> getEnseignants(Type enseignant);
    @Query("SELECT P FROM Person P WHERE P.personId = ?1")
    Person getEnseignant(Long enseignantId);
    @Query("SELECT P FROM Person P WHERE P.cin = ?1")
    Person getEnseignantByCin(String cin);
    @Query("SELECT P FROM Person P WHERE P.email = ?1")
    Person getEnseignantByEmail(String email);


//    @Query("SELECT P FROM Person P WHERE P.personId = ?1")
//    Person getEnseignantByCin(Long enseignantId);







}
