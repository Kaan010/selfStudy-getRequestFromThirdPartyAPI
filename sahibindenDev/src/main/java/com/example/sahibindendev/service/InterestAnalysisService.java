package com.example.sahibindendev.service;

import com.example.sahibindendev.model.AccessLog;
import com.example.sahibindendev.model.CategoryType;
import com.example.sahibindendev.model.Classified;
import com.example.sahibindendev.model.InterestAnalysis;
import com.example.sahibindendev.repository.InterestAnalysisRepository;
import com.example.sahibindendev.util.PredictCalculateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestAnalysisService {

    private final InterestAnalysisRepository interestAnalysisRepository;
    private final AccessLogService accessLogService;
    private final ClassifiedService classifiedService;

    public InterestAnalysisService(InterestAnalysisRepository interestAnalysisRepository, AccessLogService accessLogService, ClassifiedService classifiedService) {
        this.interestAnalysisRepository = interestAnalysisRepository;
        this.accessLogService = accessLogService;
        this.classifiedService = classifiedService;
    }

    public InterestAnalysis getInterestAnalysisOfUserId(String userId) {
        InterestAnalysis interestAnalysisByUserId = createInterestAnalysisByUserId(userId);
        saveInterestAnalysisByUserId(interestAnalysisByUserId);
        return interestAnalysisByUserId;
    }

    private void saveInterestAnalysisByUserId(InterestAnalysis interestAnalysis) {
        interestAnalysisRepository.saveAndFlush(interestAnalysis);
    }

    private InterestAnalysis createInterestAnalysisByUserId(String userid) {
        List<AccessLog> accessLogsByUserId = accessLogService.getAccessLogsByUserId(userid);
        List<CategoryType> allAccessedCategoriesOfUser = getAllAccessedCategoriesOfUser(accessLogsByUserId);
        List<Integer> allCheckedPricesOfUser = getAllCheckedPricesOfUser(accessLogsByUserId);
        return new InterestAnalysis(
                userid,
                PredictCalculateUtil.calculatePossibleInterest(allAccessedCategoriesOfUser),
                PredictCalculateUtil.calculatePossibleBudget(allCheckedPricesOfUser)
        );
    }

    private List<CategoryType> getAllAccessedCategoriesOfUser(List<AccessLog> accessLogsByUserId) {
        return accessLogsByUserId.stream()
                .filter(log -> log.getEndPoint().contains("/classifieds/"))
                .map(log -> classifiedService.getClassifiedById(getClassifiedIdFromEndPoint(log.getEndPoint())))
                .map(Classified::getCategory)
                .map(CategoryType::valueOf)
                .collect(Collectors.toList());
    }

    private List<Integer> getAllCheckedPricesOfUser(List<AccessLog> accessLogsByUserId) {
        return accessLogsByUserId.stream()
                .filter(log -> log.getEndPoint().contains("/classifieds/"))
                .map(log -> classifiedService.getClassifiedById(getClassifiedIdFromEndPoint(log.getEndPoint())))
                .map(Classified::getCurrency)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private String getClassifiedIdFromEndPoint(String endPoint) {
        String[] split = endPoint.split("/");
        return split[split.length - 1];

    }


}
