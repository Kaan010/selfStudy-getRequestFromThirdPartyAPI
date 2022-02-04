package com.example.sahibindendev.service;

import com.example.sahibindendev.model.AccessLogDTO;
import com.example.sahibindendev.model.CategoryType;
import com.example.sahibindendev.model.ClassifiedDTO;
import com.example.sahibindendev.model.InterestAnalysis;
import com.example.sahibindendev.repository.InterestAnalysisRepository;
import com.example.sahibindendev.util.PredictCalculateUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public InterestAnalysis getInterestAnalysisOfUserId(Long userId) {
        InterestAnalysis interestAnalysisByUserId = createInterestAnalysisByUserId(userId);
        saveInterestAnalysisByUserId(interestAnalysisByUserId);
        return interestAnalysisByUserId;
    }

    private void saveInterestAnalysisByUserId(InterestAnalysis interestAnalysis) {
        interestAnalysisRepository.saveAndFlush(interestAnalysis);
    }

    private InterestAnalysis createInterestAnalysisByUserId(Long userid) {
        List<AccessLogDTO> accessLogsByUserIdDTO = accessLogService.getAccessLogsByUserId(userid);
        List<CategoryType> allAccessedCategoriesOfUser = getAllAccessedCategoriesOfUser(accessLogsByUserIdDTO);
        List<Double> allCheckedPricesOfUser = getAllCheckedPricesOfUser(accessLogsByUserIdDTO);
        return new InterestAnalysis(
                userid,
                PredictCalculateUtil.calculatePossibleInterest(allAccessedCategoriesOfUser),
                PredictCalculateUtil.calculatePossibleBudget(allCheckedPricesOfUser)
        );
    }

    private List<CategoryType> getAllAccessedCategoriesOfUser(List<AccessLogDTO> accessLogsByUserIdDTO) {
        return accessLogsByUserIdDTO.parallelStream()
                .filter(log -> log.getEndPoint().contains("/classifieds/"))
                .map(log -> classifiedService.getClassifiedById(getClassifiedIdFromEndPoint(log.getEndPoint())))
                .map(ClassifiedDTO::getCategory)
                .map(CategoryType::valueOf)
                .collect(Collectors.toList());
    }
//FIXME: Up and Down side methods go 2 times DB. It's wrong I know but I have no time to think for it
    private List<Double> getAllCheckedPricesOfUser(List<AccessLogDTO> accessLogsByUserIdDTO) {
        return accessLogsByUserIdDTO.parallelStream()
                .filter(log -> log.getEndPoint().contains("/classifieds/"))
                .map(log -> classifiedService.getClassifiedById(getClassifiedIdFromEndPoint(log.getEndPoint())))
                .map(ClassifiedDTO::getPrice)
                .collect(Collectors.toList());
    }

    private String getClassifiedIdFromEndPoint(String endPoint) {
        String[] split = endPoint.split("/");
        return split[split.length - 1];

    }


}
