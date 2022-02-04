package com.example.sahibindendev.repository;

import com.example.sahibindendev.model.InterestAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestAnalysisRepository extends JpaRepository<InterestAnalysis,String> {

    //not used for now
    Optional<InterestAnalysis> findInterestAnalysisByUserId(String userId);
}
