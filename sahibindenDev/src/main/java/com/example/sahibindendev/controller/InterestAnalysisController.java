package com.example.sahibindendev.controller;

import com.example.sahibindendev.model.InterestAnalysis;
import com.example.sahibindendev.service.InterestAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/interest")
public class InterestAnalysisController {

    private final InterestAnalysisService interestAnalysisService;
    public InterestAnalysisController(InterestAnalysisService interestAnalysisService) {
        this.interestAnalysisService = interestAnalysisService;
    }


    @GetMapping("/{userid}")
    public ResponseEntity<InterestAnalysis> getInterestAnalysisOfUser(@PathVariable String userid) {
        return new ResponseEntity<>(
                interestAnalysisService.getInterestAnalysisOfUserId(userid),
                HttpStatus.OK
        );
    }
}
