package com.admi.MiniPorject.controllers;

import com.admi.MiniPorject.models.*;
import com.admi.MiniPorject.models.order.IsValide;
import com.admi.MiniPorject.services.FournitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "fourniture")
@CrossOrigin
public class FournitureController {
    private final FournitureService service;

    @Autowired
    public FournitureController(FournitureService service) {
        this.service = service;
    }

    @GetMapping(path = "{fournitureId}")
    public Fourniture getFourniture(@PathVariable("fournitureId") Long fournitureId){
        Fourniture fourniture = service.getFourniture(fournitureId);
        fourniture = fixJson(fourniture);
        return fourniture;
    }

    private static Fourniture fixJson(Fourniture fourniture) {
        fourniture.getOrders().forEach(fournitureOrder -> {
            fournitureOrder.setFourniture(null);
            fournitureOrder.getPerson().setFournitureOrders(null);
            fournitureOrder.getPerson().setMaterialOrders(null);
        });
        return fourniture;
    }


    @GetMapping(path = "A")
    public List<Fourniture> getFournituresForAdmin(){
        List<Fourniture> fournitures = service.getFournitures();
        fournitures = fournitures.stream().map(FournitureController::fixJson).collect(Collectors.toList());
        return fournitures;
    }

    @GetMapping
    public List<Fourniture> getFournitures(){
        List<Fourniture> fournitures = service.getFournitures();
//        List<Fourniture> filteredFourniture = fournitures.stream().filter(fourniture ->{
//            if(!fourniture.getOrders().isEmpty()){
//                List<FournitureOrder> ms = fourniture.getOrders().stream().filter(fournitureOrder-> {
//                    if( fournitureOrder.getIsAccepted() != null)
//                        return  fournitureOrder.getIsAccepted();
//                    return false;
//                }).toList();
//                return ms.size() == 0;
//            }else
//                return true;
//        }).collect(Collectors.toList());
//        filteredFourniture = filteredFourniture.stream().map(FournitureController::fixJson).collect(Collectors.toList());
        fournitures = fournitures.stream().map(FournitureController::fixJson).collect(Collectors.toList());
        return fournitures;
    }

    @PostMapping
    public IsValide addFourniture(@RequestBody Map<String,String> fournitureBody){
        IsValide addingTest = new IsValide();
        Fourniture fourniture = Fourniture.builder()
                .type(fournitureBody.get("type"))
                .nombre(Integer.parseInt(fournitureBody.get("nombre")))
                .build();
        addingTest.setMsg("the operation is done successfully !");
        addingTest.setValideOperation(true);
        service.saveOrUpdateFourniture(fourniture);
        return addingTest;
    }
    @DeleteMapping(value = "{id}")
    public IsValide removeFourniture(@PathVariable Long id) {
        IsValide removingTest = new IsValide();
        removingTest.setMsg("the Fourniture is deleted successfully");
        removingTest.setValideOperation(true);
        service.deleteFourniture(id);
        return removingTest;
    }

    @PutMapping
    public IsValide updateFourniture(@RequestBody Map<String,String> request) {
        Fourniture fourniture = service.getFourniture(Long.parseLong(request.get("fournitureId")));
        IsValide updatingTest = new IsValide();
        fourniture.setNombre(Integer.parseInt(request.get("nombre")));
        fourniture.setType(request.get("type"));
        service.saveOrUpdateFourniture(fourniture);
        updatingTest.setMsg("the update is done successfully !");
        updatingTest.setValideOperation(true);
        return updatingTest;
    }
}
