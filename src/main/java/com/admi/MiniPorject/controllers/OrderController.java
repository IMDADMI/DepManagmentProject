package com.admi.MiniPorject.controllers;

import com.admi.MiniPorject.models.*;
import com.admi.MiniPorject.services.EnseignantService;
import com.admi.MiniPorject.services.FournitureService;
import com.admi.MiniPorject.services.MaterielService;
import com.admi.MiniPorject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "order")
@CrossOrigin

public class OrderController {

    private final OrderService service;
    private final MaterielService materielService;
    private final FournitureService fournitureService;
    private final EnseignantService enseignantService;
    @Autowired
    public OrderController(OrderService service0,MaterielService service1,EnseignantService service2,FournitureService service3) {
        this.service = service0;
        this.materielService = service1;
        this.enseignantService = service2;
        this.fournitureService = service3;
    }



    @GetMapping(value = "material")
    public List<MaterialOrder> getMaterielOrders(){
        List<MaterialOrder> orders = service.GetMaterielOrders();
        orders = orders.stream().map(OrderController::materJsonFix).collect(Collectors.toList());
        return orders;
    }
    @GetMapping(value = "fourniture")
    public List<FournitureOrder> getFournitureOrders(){
        List<FournitureOrder> orders = service.GetFournitureOrders();
        orders = orders.stream().map(OrderController::fourJsonFix).collect(Collectors.toList());
        return orders;
    }

    public static FournitureOrder fourJsonFix(FournitureOrder order){
        order.getPerson().setMaterialOrders(null);
        order.getPerson().setFournitureOrders(null);
        order.getFourniture().setOrders(null);
        return order;
    }

    public static MaterialOrder materJsonFix(MaterialOrder order){
        order.getPerson().setMaterialOrders(null);
        order.getPerson().setFournitureOrders(null);
        order.getMaterial().setOrders(null);
        return order;
    }
    @PostMapping(value = "material")
    public MaterialOrder saveMaterielOrder(@RequestBody Map<String,String> ids){
        System.out.println(ids);
        String materielId = ids.get("materielId");
        String personId = ids.get("personId");
        Material material = materielService.getMateriel(Long.valueOf(materielId));
        Person p = enseignantService.getEnseignant(Long.valueOf(personId));
        //check if the material is already ordered
        MaterialOrder order = new MaterialOrder();
        return service.saveMaterielOrder(p,order,material);
    }

    @PostMapping(value = "fourniture")
    public FournitureOrder saveFournitureOrder(@RequestBody Map<String,String> ids){
        String fournitureId = ids.get("fournitureId");
        String personId = ids.get("personId");
        String numberOfFourniture = ids.get("nombre");

        Fourniture fourniture = fournitureService.getFourniture(Long.valueOf(fournitureId));
        Person p = enseignantService.getEnseignant(Long.valueOf(personId));
        //check if the fourniture is already ordered
        FournitureOrder order = new FournitureOrder();
        order.setNumber(Integer.parseInt(numberOfFourniture));
        return service.saveFournitureOrder(p,order,fourniture);
    }

    @DeleteMapping(path = "material/{materialId}")
    public void deleteMaterial(@PathVariable String materialId){
        service.deleteMaterielOrder(Long.valueOf(materialId));
    }

    @DeleteMapping(path = "fourniture/{fournitureId}")
    public void deleteFourniture(@PathVariable String fournitureId){
        service.deleteFournitureOrder(Long.valueOf(fournitureId));
    }


    @DeleteMapping(value = "material")
    public void deleteAllMaterial(){
        service.deleteAllMateriels();
    }

    @DeleteMapping(value = "fourniture")
    public void deleteAllFourniture(){
        service.deleteAllFournitures();
    }


}
