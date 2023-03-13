package com.admi.MiniPorject.services;

import com.admi.MiniPorject.models.Person;
import com.admi.MiniPorject.models.enums.Type;
import com.admi.MiniPorject.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignantService {
    private final EnseignantRepository repository;

    @Autowired
    public EnseignantService(EnseignantRepository repository) {
        this.repository = repository;
    }

    public List<Person> getEnseignants(){
        return repository.getEnseignants(Type.enseignant);
    }

    public Person getEnseignant(Long id){
        return repository.getEnseignant(id);
    }

    public void saveOrUpdateEnseignant(Person enseignant){
        repository.save(enseignant);
    }
    public void deleteEnseignant(Long id){
        repository.deleteById(id);
    }

    public Person getEnseignantByCin(String cin){
        return repository.getEnseignantByCin(cin);
    }

    public Person getEnseignantByEmail(String email){
        return repository.getEnseignantByEmail(email);
    }

}
