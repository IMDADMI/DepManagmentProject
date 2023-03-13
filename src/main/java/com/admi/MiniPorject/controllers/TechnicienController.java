package com.admi.MiniPorject.controllers;

import com.admi.MiniPorject.models.FournitureOrder;
import com.admi.MiniPorject.models.MaterialOrder;
import com.admi.MiniPorject.models.enums.Grade;
import com.admi.MiniPorject.models.enums.Type;
import com.admi.MiniPorject.models.order.IsValide;
import com.admi.MiniPorject.models.Person;
import com.admi.MiniPorject.repositories.TechnicienRepository;
import com.admi.MiniPorject.services.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "technicien")
@CrossOrigin
public class TechnicienController implements Serializable {
   private final TechnicienService service;
    private final TechnicienRepository technicienRepository;


    @Autowired
    public TechnicienController(TechnicienService service,
                                TechnicienRepository technicienRepository) {
        this.service = service;
        this.technicienRepository = technicienRepository;
    }

    @GetMapping(path = "{TechnicienId}")
    public Person getTechnicien(@PathVariable("TechnicienId") Long TechnicienId){
        Person Technicien = service.getTechnicien(TechnicienId);
        Technicien = EnseignantController.fixJson(Technicien);
        return Technicien;
    }


    @GetMapping
    public List<Person> getTechniciens(){
        List<Person> Techniciens = service.getTechniciens();
        Techniciens = Techniciens.stream().map(EnseignantController::fixJson).collect(Collectors.toList());
        return Techniciens;
    }

    @PostMapping
    public IsValide addTechnicien(@RequestBody Map<String,String> technicien){
        IsValide addingTest = new IsValide();
        addingTest.setMsg("the operation is done successfully !");
        addingTest.setValideOperation(true);
        Person Technicien;

        String enGrade = technicien.get("grade");
        Technicien = Person.builder().
                cin(technicien.get("cin")).
                nom(technicien.get("nom")).
                email(technicien.get("email")).
                prenom(technicien.get("prenom")).
                numeroBureau(Integer.parseInt(technicien.get("numeroBureau"))).
                telephone(technicien.get("telephone"))
                .grade(Grade.valueOf(enGrade))
                .type(Type.technicien).build();
        service.saveOrUpdateTechnicien(Technicien);

        return addingTest;
    }

    @DeleteMapping(value = "{id}")
    public IsValide removeTechnicien(@PathVariable Long id) {
        IsValide removingTest = new IsValide();
        removingTest.setMsg("the Technicien is deleted successfully");
        removingTest.setValideOperation(true);
        service.deleteTechnicien(id);
        return removingTest;
    }

    @PutMapping
    public IsValide updateTechnicien(@RequestBody Map<String,String> Technicien) {
        Person technicien = service.getTechnicien(Long.parseLong(Technicien.get("personId")));
        technicien.setNom(Technicien.get("nom"));
        technicien.setPrenom(Technicien.get("prenom"));
        technicien.setCin(Technicien.get("cin"));
        technicien.setEmail(Technicien.get("email"));
        technicien.setTelephone(Technicien.get("telephone"));
        technicien.setGrade(Grade.valueOf((Technicien.get("grade"))));
        technicien.setNumeroBureau(Integer.parseInt(Technicien.get("numeroBureau")));
        //try catch for verify the operation
        IsValide updatingTest = new IsValide();
        updatingTest.setMsg("the update is done successfully !");
        updatingTest.setValideOperation(true);
        service.saveOrUpdateTechnicien(technicien);
        return updatingTest;
    }


}
