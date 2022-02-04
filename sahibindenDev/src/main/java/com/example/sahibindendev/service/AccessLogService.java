package com.example.sahibindendev.service;

import com.example.sahibindendev.exception.ClassifiedNotFoundException;
import com.example.sahibindendev.model.AccessLog;
import com.example.sahibindendev.model.Classified;
import com.example.sahibindendev.repository.AccessLogRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccessLogService {

    private final AccessLogRepository accessLogRepository;

    public AccessLogService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    public List<AccessLog> saveAllAccessLogsComesFromSahibindenApi(){
        return accessLogRepository.saveAllAndFlush(getAllAccessLogsFromApi());
        //TODO: Save all Classifieds from sahibinden
    }

    protected List<AccessLog> getAccessLogsByUserId(String userId){
        return accessLogRepository.findAllByUsersId(userId)
                .orElseThrow(() -> new ClassifiedNotFoundException("Classified could not found with id " + userId));
    }

    private List<AccessLog> getAllAccessLogsFromApi() {
        return Collections.emptyList();
        //TODO: Get all Classifieds from sahibinden
    }

    //TO TEST
    public List<AccessLog> getAllAccessLogsFromOurDb(){
        return accessLogRepository.findAll();
    }

}
