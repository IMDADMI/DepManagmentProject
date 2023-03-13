package com.admi.MiniPorject.controllers;
import com.admi.MiniPorject.models.FournitureOrder;
import com.admi.MiniPorject.models.MaterialOrder;
import com.admi.MiniPorject.models.enums.Grade;
import com.admi.MiniPorject.models.enums.Type;
import com.admi.MiniPorject.models.order.IsValide;
import com.admi.MiniPorject.models.Person;
import com.admi.MiniPorject.services.EnseignantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "enseignant")
@CrossOrigin
public class EnseignantController {

    private final EnseignantService service;


    @Autowired
    public EnseignantController(EnseignantService service) {
        this.service = service;
    }

    @GetMapping(path = "{EnseignantId}")
    public Person getEnseignant(@PathVariable("EnseignantId") Long EnseignantId) {
        Person Enseignant = service.getEnseignant(EnseignantId);
        Enseignant = fixJson(Enseignant);
        return Enseignant;
    }

    // cett method nous permet de resoudre le problem de la recursion des donne 
    //c-a-d quand nous recuperent les donne a partir de BD L'object person contient un order (material ou fourniture)
    //donc ce object lui meme contient un person donc par consequence person va recupere order et order va recuperer person et ansi de suite
    //donc pour resoudre ce problem nous limitons cette recursion par mettre les object redondant null
    public static Person fixJson(Person e) {
        for (FournitureOrder fournitureOrder : e.getFournitureOrders()) {
            fournitureOrder.setPerson(null);
            fournitureOrder.getFourniture().setOrders(null);
        }
        for (MaterialOrder materialOrder : e.getMaterialOrders()) {
            materialOrder.setPerson(null);
            materialOrder.getMaterial().setOrders(null);
        }
        return e;
    }

    private List<FournitureOrder> filterFournitureOrder(List<FournitureOrder> fournitureOrders) {
        return fournitureOrders.stream().filter(FournitureOrder::getIsAccepted).collect(Collectors.toList());
    }

    private List<MaterialOrder> filterMaterialOrder(List<MaterialOrder> materialOrders) {
        return materialOrders.stream().filter(MaterialOrder::getIsAccepted).collect(Collectors.toList());
    }

    @GetMapping
    public List<Person> getEnseignants() {
        List<Person> Enseignants = service.getEnseignants();
        Enseignants = Enseignants.stream().map(EnseignantController::fixJson).collect(Collectors.toList());
        return Enseignants;
    }

    @PostMapping
    public IsValide addEnseignant(@RequestBody Map<String, String> Enseignant) {
        IsValide addingTest = new IsValide();
        addingTest.setMsg("the operation is done successfully !");
        Person enseignant;
        String enGrade = Enseignant.get("grade");
        enseignant = Person.builder().
                cin(Enseignant.get("cin")).
                nom(Enseignant.get("nom")).
                email(Enseignant.get("email")).
                prenom(Enseignant.get("prenom")).
                numeroBureau(Integer.parseInt(Enseignant.get("numeroBureau"))).
                telephone(Enseignant.get("telephone"))
                .grade(Grade.valueOf(enGrade))
                .type(Type.enseignant).build();

        System.out.println(enseignant);

        service.saveOrUpdateEnseignant(enseignant);
        return addingTest;
    }

    @PostMapping(path = "login")
    public Person getEnseignant(@RequestBody Map<String, String> EnseignantInfo) {
        System.out.println("inside ......");
        String email = EnseignantInfo.get("email");
        String password = EnseignantInfo.get("password");
        Person p = service.getEnseignantByEmail(email);
        System.out.println(p.toString());
        if (p == null)
            return null;

        fixJson(p);
        if (p.getCin().equals(password))
            return p;

        return null;
    }

    @DeleteMapping(value = "{id}")
    public IsValide removeEnseignant(@PathVariable Long id) {
        IsValide removingTest = new IsValide();
        removingTest.setMsg("the Enseignant is deleted successfully");
        removingTest.setValideOperation(true);
        service.deleteEnseignant(id);

        return removingTest;
    }

    @PutMapping
    public IsValide updateEnseignant(@RequestBody Map<String, String> Enseignant) {
        System.out.println(Enseignant);
        Person enseignant = service.getEnseignant(Long.parseLong(Enseignant.get("personId")));
        enseignant.setNom(Enseignant.get("nom"));
        enseignant.setPrenom(Enseignant.get("prenom"));
        enseignant.setCin(Enseignant.get("cin"));
        enseignant.setEmail(Enseignant.get("email"));
        enseignant.setTelephone(Enseignant.get("telephone"));
        enseignant.setNumeroBureau(Integer.parseInt(Enseignant.get("numeroBureau")));
        IsValide updatingTest = new IsValide();
        updatingTest.setMsg("the update is done successfully !");
        updatingTest.setValideOperation(true);
        service.saveOrUpdateEnseignant(enseignant);
        return updatingTest;
    }
}

