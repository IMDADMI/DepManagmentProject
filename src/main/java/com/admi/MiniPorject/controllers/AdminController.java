package com.admi.MiniPorject.controllers;

import com.admi.MiniPorject.models.FournitureOrder;
import com.admi.MiniPorject.models.MaterialOrder;
import com.admi.MiniPorject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin/order")
@CrossOrigin
public class AdminController {

    private final OrderService service;

    @Autowired
    public AdminController(OrderService orderService) {
        this.service = orderService;
    }

    @PostMapping(value = "materialAccept/{orderId}")
    public Boolean acceptMOrder(@PathVariable Long orderId){
        MaterialOrder order = service.GetMaterielOrder(orderId);
        order.setIsAccepted(true);
        saveMorder(order);
        return true;
    }
    //refuser un demand d'un material
    @PostMapping(value = "materialRefuse/{orderId}")
    public Boolean refuseMOrder(@PathVariable Long orderId){
        MaterialOrder order = service.GetMaterielOrder(orderId);
        order.setIsAccepted(false);
        saveMorder(order);
        return true;
    }

    private void saveMorder(MaterialOrder order) {
        service.saveMaterielOrder(order.getPerson(), order, order.getMaterial());
    }

    @DeleteMapping(value = "material/{orderId}")
    public Boolean deleteMOrder(@PathVariable Long orderId){
        service.deleteMaterielOrder(orderId);
        return false;
    }
    //accepter un fourniture par le id de demmand
    @PostMapping(value = "fournitureAccept/{orderId}")
    public Boolean acceptFOrder(@PathVariable Long orderId){
        FournitureOrder fournitureOrder = service.GetFournitureOrder(orderId);
        Integer numberOfFourniture = fournitureOrder.getNumber();
        Integer quantity = fournitureOrder.getFourniture().getNombre();
        if(quantity < numberOfFourniture)
            return false;
        if(quantity>0){
            fournitureOrder.setIsAccepted(true);
            fournitureOrder.getFourniture().setNombre(quantity-numberOfFourniture);
            saveForder(fournitureOrder);
            return true;
        }
        return false;
    }

    private void saveForder(FournitureOrder fournitureOrder) {
        service.saveFournitureOrder(fournitureOrder.getPerson(), fournitureOrder, fournitureOrder.getFourniture());
    }

    @PostMapping(value = "fournitureRefuse/{orderId}")
    public Boolean refuseFOrder(@PathVariable Long orderId){
        FournitureOrder fournitureOrder = service.GetFournitureOrder(orderId);
        fournitureOrder.setIsAccepted(false);
        saveForder(fournitureOrder);
        return false;
    }
    @DeleteMapping(value = "fourniture/{orderId}")
    public Boolean deleteFOrder(@PathVariable Long orderId){
        service.deleteFournitureOrder(orderId);
        return true;
    }

}
