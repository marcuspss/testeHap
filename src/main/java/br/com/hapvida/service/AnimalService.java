package br.com.hapvida.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hapvida.model.Animal;
import br.com.hapvida.repository.AnimalRepository;

@Service
public class AnimalService {


    @Autowired
    AnimalRepository animalRepository;

    public Page<Animal> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("next")){
            return animalRepository.findByDataNascimentoAfterOrderByDataNascimentoAsc(LocalDateTime.now(), pageable);
        }else if(flag != null && flag.equals("previous")){
            return animalRepository.findByDataNascimentoBeforeOrderByDataNascimentoDesc(LocalDateTime.now(), pageable);
        }else{
            return animalRepository.findAll(pageable);
        }
    }

    public Optional<Animal> findById(Long id){
        return animalRepository.findById(id);
    }

    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    public void delete(Animal animal){
    	animalRepository.delete(animal);
    }
    
    public void deleteById(Long id){
    	animalRepository.deleteById(id);
    }
}
