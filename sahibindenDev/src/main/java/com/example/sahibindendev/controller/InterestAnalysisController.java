package com.example.sahibindendev.controller;

import com.example.sahibindendev.model.InterestAnalysis;
import com.example.sahibindendev.service.InterestAnalysisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/interest")
public class InterestAnalysisController {

    private final InterestAnalysisService interestAnalysisService;
    public InterestAnalysisController(InterestAnalysisService interestAnalysisService) {
        this.interestAnalysisService = interestAnalysisService;
    }


    //CALLME LAST
    //****OUR END POINT****
    @PostMapping("/{userid}")
    public ResponseEntity<InterestAnalysis> getInterestAnalysisOfUser(@PathVariable Long userid) {
        return new ResponseEntity<>(
                interestAnalysisService.getInterestAnalysisOfUserId(userid),
                HttpStatus.OK
        );
    }
}
