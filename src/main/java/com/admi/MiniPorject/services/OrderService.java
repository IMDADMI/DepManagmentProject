package com.admi.MiniPorject.services;

import com.admi.MiniPorject.controllers.FournitureController;
import com.admi.MiniPorject.controllers.OrderController;
import com.admi.MiniPorject.models.*;
import com.admi.MiniPorject.repositories.FournitureOrderRepository;
import com.admi.MiniPorject.repositories.MaterielOrderRepository;
import com.admi.MiniPorject.repositories.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final FournitureOrderRepository fournitureOrderRepository;
    private final MaterielOrderRepository materielOrderRepository;
    private final MaterielRepository repository;

    @Autowired
    public OrderService(MaterielRepository repository,FournitureOrderRepository fournitureOrderRepository, MaterielOrderRepository materielOrderRepository) {
        this.fournitureOrderRepository = fournitureOrderRepository;
        this.materielOrderRepository = materielOrderRepository;
        this.repository = repository;
    }

    public MaterialOrder saveMaterielOrder(Person person, MaterialOrder order, Material material){
        person.getMaterialOrders().add(order);
        material.getOrders().add(order);
        order.setPerson(person);
        order.setMaterial(material);
        order.setDateOrder(LocalDate.now());
        order = OrderController.materJsonFix(order);
        repository.save(material);
        materielOrderRepository.save(order);
        return order;
    }

    public FournitureOrder saveFournitureOrder(Person person, FournitureOrder order,Fourniture fourniture){
        person.getFournitureOrders().add(order);
        fourniture.getOrders().add(order);
        order.setPerson(person);
        order.setFourniture(fourniture);
        order.setDateOrder(LocalDate.now());
        order = OrderController.fourJsonFix(order);
        fournitureOrderRepository.save(order);
        return order;
    }

    public Fourniture getFournitureById (Long id) {
        Fourniture f = fournitureOrderRepository.getFournitureById(id);
        return f;
    }

    public List<MaterialOrder> GetMaterielOrders(){
        List<MaterialOrder> orders = materielOrderRepository.findAll();
        return orders;
    }
    public MaterialOrder GetMaterielOrder(Long id){
        MaterialOrder order = materielOrderRepository.getMaterialOrderById(id);
        return order;
    }
    public FournitureOrder GetFournitureOrder(Long id) {
        FournitureOrder order = fournitureOrderRepository.getFournitureOrderById(id);
        return order;
    }

    public List<FournitureOrder> GetFournitureOrders() {
        List<FournitureOrder> orders = fournitureOrderRepository.findAll();
        return orders;
    }
    public void deleteMaterielOrder(Long id){
        materielOrderRepository.deleteById(id);
    }
    public void deleteAllMateriels(){
        materielOrderRepository.deleteAll();
    }
    public void deleteFournitureOrder(Long id){
        fournitureOrderRepository.deleteById(id);
    }
    public void deleteAllFournitures(){
        fournitureOrderRepository.deleteAll();
    }
}
