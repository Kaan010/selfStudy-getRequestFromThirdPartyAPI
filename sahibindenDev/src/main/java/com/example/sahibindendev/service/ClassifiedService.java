package com.example.sahibindendev.service;

import com.example.sahibindendev.exception.ClassifiedNotFoundException;
import com.example.sahibindendev.model.Classified;
import com.example.sahibindendev.repository.ClassifiedRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassifiedService {

    private final ClassifiedRepository classifiedRepository;

    public ClassifiedService(ClassifiedRepository classifiedRepository) {
        this.classifiedRepository = classifiedRepository;
    }



    public List<Classified> saveAllClassifiedsThatComeFromSahibindenApi(){
        return classifiedRepository.saveAllAndFlush(getAllClassifiedsFromApi());
        //TODO: Save all Classifieds from sahibinden
    }

    private List<Classified> getAllClassifiedsFromApi() {
        return Collections.emptyList();
        //TODO: Get all Classifieds from sahibinden
    }



    //TO TEST
    public List<Classified> getAllClassifiedsFromOurDb(){
        return classifiedRepository.findAll();
    }

    //Will be called  from InterestAnalysis service
    protected List<Classified> getAllClassifiedsByUserId(String userId){
        return classifiedRepository.findAllByUsersId(userId)
                .orElseThrow(() -> new ClassifiedNotFoundException("Classified could not found with id " + userId));
    }
}
