package com.example.sahibindendev.controller;

import com.example.sahibindendev.model.AccessLog;
import com.example.sahibindendev.model.Classified;
import com.example.sahibindendev.service.AccessLogService;
import com.example.sahibindendev.service.ClassifiedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accesslog")
public class AccessLogController {

    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    //CALLME FIRST
    //update to our DB with sahibindens DB for accessLog
    @PostMapping("/update/")
    public ResponseEntity<List<AccessLog>> saveAllClassifiedsComesFromSahibinden() {
        return new ResponseEntity<>(
                accessLogService.saveAllAccessLogsComesFromSahibindenApi(),
                HttpStatus.CREATED
        );
    }


    //TO TEST
    @GetMapping
    public ResponseEntity<List<AccessLog>> getAllClassifieds() {
        return new ResponseEntity<>(
                accessLogService.getAllAccessLogsFromOurDb(),
                HttpStatus.OK
        );
    }
}
