package com.admi.MiniPorject.controllers;

import com.admi.MiniPorject.models.Material;
import com.admi.MiniPorject.models.MaterialOrder;
import com.admi.MiniPorject.repositories.MaterielRepository;
import com.admi.MiniPorject.services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;
import com.admi.MiniPorject.models.order.IsValide;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "material")
@CrossOrigin

public class MaterielController {

    private final MaterielService service;
    private final MaterielRepository materielRepository;

    @Autowired
    public MaterielController(MaterielService service,
                              MaterielRepository materielRepository) {
        this.service = service;
        this.materielRepository = materielRepository;
    }

    @GetMapping(path = "{materielId}")
    public Material getMateriel(@PathVariable("materielId") Long materielId){
        Material material = service.getMateriel(materielId);
        material = fixJson(material);
        return material;
    }

    private static Material fixJson(Material material) {
        material.getOrders().forEach(materialOrder -> {
            materialOrder.setMaterial(null);
            materialOrder.getPerson().setFournitureOrders(null);
            materialOrder.getPerson().setMaterialOrders(null);
        });
        return material;
    }


    @GetMapping(path = "A")
    public List<Material> getMaterielsForAdmin(){
        List<Material> materials = service.getMateriels();
        materials = materials.stream().map(MaterielController::fixJson).collect(Collectors.toList());
        return materials;
    }

    @GetMapping
    public List<Material> getMateriels(){
        List<Material> materials = service.getMateriels();
        List<Material> filteredMaterials = materials.stream().filter(material ->{
            System.out.println("Type : "+material.getType());
            if(!material.getOrders().isEmpty()){
                List<MaterialOrder> ms = material.getOrders().stream().filter(MaterialOrder-> {
                    System.out.println(MaterialOrder.getMaterielOrderId());
                    if( MaterialOrder.getIsAccepted() != null){
                       return  MaterialOrder.getIsAccepted();
                    }
                    return false;
                }).toList();

                return ms.size() == 0;
            }else
                return true;
        }).collect(Collectors.toList());
        filteredMaterials = filteredMaterials.stream().map(MaterielController::fixJson).collect(Collectors.toList());

        return filteredMaterials;

    }
    @PostMapping
    public IsValide addMateriel(@RequestBody Map<String,String> materialBody){
        Material material = Material.builder()
                .numeroInventaire(Long.valueOf(materialBody.get("numeroInventaire")))
                .dateAffectation(LocalDate.parse(materialBody.get("dateAcquisition")))
                .dateAcquisition(LocalDate.parse(materialBody.get("dateAcquisition")))
                .type(materialBody.get("type")).build();

        IsValide addingTest = new IsValide();

        System.out.println(material.getDateAcquisition());
        System.out.println(material.getNumeroInventaire());
        addingTest.setMsg("the operation is done successfully !");
        addingTest.setValideOperation(true);
        service.saveOrUpdateMateriel(material);
        return addingTest;
    }




    @DeleteMapping(value = "{id}")
    public IsValide removeMateriel(@PathVariable Long id) {
        IsValide removingTest = new IsValide();
        removingTest.setMsg("the materiel is deleted successfully");
        removingTest.setValideOperation(true);
        service.deleteMateriel(id);
        return removingTest;
    }

//    @PutMapping
//    public IsValide updateMateriel(@ModelAttribute Material material) {
//        //try catch for verify the operation
//        IsValide updatingTest = new IsValide();
//        updatingTest.setMsg("the update is done successfully !");
//        updatingTest.setValideOperation(true);
//        service.saveOrUpdateMateriel(material);
//        System.out.println("inside put material");
//        System.out.println(material);
//        return updatingTest;
//    }
    @PutMapping()
    public IsValide updateMateriel(@RequestBody Map<String,String> material){
        Material updatedMaterial = service.getMateriel(Long.parseLong(material.get("numeroInventaire")));
        updatedMaterial.setType(material.get("type"));
        updatedMaterial.setDateAcquisition(LocalDate.parse(material.get("dateAcquisition")));
        updatedMaterial.setDateAffectation(LocalDate.parse(material.get("dateAffectaion")));
        IsValide updatingTest = new IsValide();
        updatingTest.setMsg("the update is done successfully !");
        updatingTest.setValideOperation(true);
        return updatingTest;
    }

}
