package com.admi.MiniPorject.services;

import com.admi.MiniPorject.models.Person;
import com.admi.MiniPorject.models.enums.Type;
import com.admi.MiniPorject.repositories.EnseignantRepository;
import com.admi.MiniPorject.repositories.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicienService {

    private final TechnicienRepository repository;

    @Autowired
    public TechnicienService(TechnicienRepository repository) {
        this.repository = repository;
    }

    public List<Person> getTechniciens(){
        return repository.getTechniciens(Type.technicien);
    }

    public Person getTechnicien(Long id){
        return repository.getTechnicien(id);
    }

    public void saveOrUpdateTechnicien(Person Technicien){
        repository.save(Technicien);
    }
    public void deleteTechnicien(Long id){
        repository.deleteById(id);
    }
    public Person getTechnicienByCin(String cin){
        return repository.getTechnicienByCin(cin);
    }

}

