package com.example.sahibindendev.controller;

import com.example.sahibindendev.model.Classified;
import com.example.sahibindendev.service.ClassifiedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/classified")
public class ClassifiedController {

    private final ClassifiedService classifiedService;

    public ClassifiedController(ClassifiedService classifiedService) {
        this.classifiedService = classifiedService;
    }

    //update to our DB with sahibindens DB
    @PostMapping("/update/")
    public ResponseEntity<List<Classified>> saveAllClassifiedsComesFromSahibinden() {
        return new ResponseEntity<>(
                classifiedService.saveAllClassifiedsThatComeFromSahibindenApi(),
                HttpStatus.CREATED
        );
    }

    
    //TO TEST
    @GetMapping
    public ResponseEntity<List<Classified>> getAllClassifieds() {
        return new ResponseEntity<>(
                classifiedService.getAllClassifiedsFromOurDb(),
                HttpStatus.OK
        );
    }
}
