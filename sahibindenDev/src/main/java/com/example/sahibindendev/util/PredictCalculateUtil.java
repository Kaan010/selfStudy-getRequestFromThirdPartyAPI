package com.example.sahibindendev.util;

import com.example.sahibindendev.model.CategoryType;
import com.example.sahibindendev.model.InterestAnalysis;

import java.util.HashMap;
import java.util.List;

public class PredictCalculateUtil {
    public static CategoryType calculatePossibleInterest(List<CategoryType> categoryTypes) {
        HashMap<CategoryType, Integer> categoryCounterMap = new HashMap<>();
        categoryCounterMap.put(CategoryType.REAL_ESTATE, 0);
        categoryCounterMap.put(CategoryType.SHOPPING, 0);
        categoryCounterMap.put(CategoryType.VEHICLE, 0);

        for (CategoryType categoryType : categoryTypes) {
            categoryCounterMap.put(
                    categoryType,
                    categoryCounterMap.get(categoryType) + 1
            );
        }

        CategoryType resultCategory = CategoryType.REAL_ESTATE;
        int resultCategoryCounter = 0;
        for (CategoryType category : CategoryType.values()) {
            if (categoryCounterMap.get(category) > resultCategoryCounter) {
                resultCategory = category;
                resultCategoryCounter = categoryCounterMap.get(category);
            }
        }
        return resultCategory;
    }

    public static String calculatePossibleBudget(List<Integer> pricesOfClassifieds) {
        return pricesOfClassifieds.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0.0) + "";
    }


    }
