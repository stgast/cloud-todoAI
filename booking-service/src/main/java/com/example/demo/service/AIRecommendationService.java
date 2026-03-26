package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIRecommendationService {
    
    @Value("${openai.api.key:}")
    private String apiKey;
    
    @Value("${openai.api.model:gpt-3.5-turbo}")
    private String model;
    
    /**
     * Рекомендація часу резервування на основі AI
     */
    public String recommendBookingTime(String serviceName, String userPreferences) {
        // На цей момент - простий логіку
        // Потребує OpenAI API key для повної інтеграції
        
        if (apiKey == null || apiKey.isEmpty()) {
            log.warn("OpenAI API key не налаштована - використовуємо простої логіку");
            return getDefaultRecommendation(serviceName);
        }
        
        try {
            // TODO: Реалізувати інтеграцію з OpenAI API
            // return callOpenAIAPI(serviceName, userPreferences);
            return getDefaultRecommendation(serviceName);
        } catch (Exception e) {
            log.error("Помилка при отриманні рекомендації від AI", e);
            return getDefaultRecommendation(serviceName);
        }
    }
    
    /**
     * Генерація описання послуги
     */
    public String generateServiceDescription(String serviceName) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "Послуга: " + serviceName;
        }
        
        try {
            // TODO: Реалізувати інтеграцію з OpenAI API для опису
            return "Послуга: " + serviceName;
        } catch (Exception e) {
            log.error("Помилка при генерації опису", e);
            return "Послуга: " + serviceName;
        }
    }
    
    private String getDefaultRecommendation(String serviceName) {
        return switch (serviceName.toLowerCase()) {
            case "консультація" -> "Рекомендуємо: вівторок-четвер, 10:00-12:00";
            case "масаж" -> "Рекомендуємо: понеділок-п'ятниця, 14:00-18:00";
            case "обстеження" -> "Рекомендуємо: будь-який робочий день, 09:00-11:00";
            default -> "Рекомендуємо: попередня координація потребується";
        };
    }
}
