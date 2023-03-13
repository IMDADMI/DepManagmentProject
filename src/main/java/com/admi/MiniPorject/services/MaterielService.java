package com.admi.MiniPorject.services;

import com.admi.MiniPorject.models.Material;
import com.admi.MiniPorject.repositories.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterielService {

    private final MaterielRepository repository;

    @Autowired
    public MaterielService(MaterielRepository repository) {
        this.repository = repository;
    }

    public Material getMateriel(Long id){
        Material material = repository.getMaterielById(id);
        return material;
    }
    public void saveOrUpdateMateriel(Material material){
        repository.save(material);
    }
    public List<Material> getMateriels(){
        List<Material> materials = repository.findAll();
        return materials;
    }

    public void deleteMateriel(Long id){
            repository.deleteById(id);
    }

}
