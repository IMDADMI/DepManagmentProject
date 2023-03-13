package com.admi.MiniPorject.services;

import com.admi.MiniPorject.models.Fourniture;
import com.admi.MiniPorject.repositories.FournitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournitureService {
    private final FournitureRepository repository;

    @Autowired
    public FournitureService(FournitureRepository repository) {
        this.repository = repository;
    }

    public void saveOrUpdateFourniture(Fourniture fourniture){
        repository.save(fourniture);
    }
    public Fourniture getFourniture(Long id){
        Fourniture fourniture = repository.getFournitureBy(id);
        return fourniture;
    }
    public List<Fourniture> getFournitures(){
        List<Fourniture> fournitures = repository.findAll();
        return fournitures;
    }

    public void deleteFourniture(Long id){
        repository.deleteById(id);

    }


}
