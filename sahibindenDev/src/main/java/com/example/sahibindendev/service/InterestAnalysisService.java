package com.example.sahibindendev.service;

import com.example.sahibindendev.model.InterestAnalysis;
import com.example.sahibindendev.repository.InterestAnalysisRepository;
import com.example.sahibindendev.util.PredictCalculateUtil;
import org.springframework.stereotype.Service;

@Service
public class InterestAnalysisService {

    private final InterestAnalysisRepository interestAnalysisRepository;
    private final ClassifiedService classifiedService;

    public InterestAnalysisService(InterestAnalysisRepository interestAnalysisRepository, ClassifiedService classifiedService) {
        this.interestAnalysisRepository = interestAnalysisRepository;
        this.classifiedService = classifiedService;
    }

    public InterestAnalysis getInterestAnalysisOfUserId(String userId){
        InterestAnalysis interestAnalysisByUserId = createInterestAnalysisByUserId(userId);
        saveInterestAnalysisByUserId(interestAnalysisByUserId);
        return interestAnalysisByUserId;
    }

    private void saveInterestAnalysisByUserId(InterestAnalysis interestAnalysis){
        interestAnalysisRepository.saveAndFlush(interestAnalysis);
    }

    private InterestAnalysis createInterestAnalysisByUserId(String userid){

        classifiedService.getAllClassifiedsByUserId(userid)
        return new InterestAnalysis(
                userid,
                PredictCalculateUtil.calculatePossibleInterest()

        )
    }


}
