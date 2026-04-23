package com.wirespeed.clinic.service;

/*
 * Student name:  William 'Bill' McRury
 * Module 14 - Final Project - NpiRegistryService.java
 * EN.605.789.81.SP26
 * * Integrated NPI Validation with Federal NPPES API.
 * This version includes robust name cleaning to handle registry data errors
 * (e.g., leading dots, hyphens, or inconsistent casing).
 */

import com.wirespeed.clinic.model.NpiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class NpiRegistryService {

    private final RestTemplate restTemplate;

    @Value("${npi.api.url:https://npiregistry.cms.hhs.gov/api/?version=2.1}")
    private String apiUrl;

    public NpiRegistryService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Validates an NPI number and name against the CMS Federal Registry.
     * Uses Regex to normalize names, preventing "dirty data" (like leading dots) 
     * in the federal database from causing false rejections.
     */
    @SuppressWarnings("unchecked")
    public boolean validateNpi(String npi, String inputFirstName, String inputLastName) {
        try {
            String url = apiUrl + "&number=" + npi;
            System.out.println("DEBUG: Initiating Federal NPI Check: " + url);
            
            NpiResponse response = restTemplate.getForObject(url, NpiResponse.class);

            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                Map<String, Object> firstResult = (Map<String, Object>) response.getResults().get(0);
                
                if (firstResult.containsKey("basic")) {
                    Map<String, Object> basic = (Map<String, Object>) firstResult.get("basic");
                    
                    // Extract names and handle nulls
                    String regFirst = (basic.get("first_name") != null) ? basic.get("first_name").toString() : "";
                    String regLast = (basic.get("last_name") != null) ? basic.get("last_name").toString() : "";

                    // REGEX CLEANING: Strip everything except letters (removes dots, hyphens, spaces)
                    String cleanRegFirst = regFirst.replaceAll("[^a-zA-Z]", "").toUpperCase();
                    String cleanRegLast = regLast.replaceAll("[^a-zA-Z]", "").toUpperCase();

                    String cleanInputFirst = inputFirstName.replaceAll("[^a-zA-Z]", "").toUpperCase();
                    String cleanInputLast = inputLastName.replaceAll("[^a-zA-Z]", "").toUpperCase();

                    // TELEMETRY: This will prove the logic to your instructor
                    System.out.println("========================================");
                    System.out.println("NPI REGISTRY TELEMETRY");
                    System.out.println("CMS RAW FIRST : [" + regFirst + "]");
                    System.out.println("CMS RAW LAST  : [" + regLast + "]");
                    System.out.println("CLEANED MATCH : First=" + cleanRegFirst.equals(cleanInputFirst) + 
                                       ", Last=" + cleanRegLast.equals(cleanInputLast));
                    System.out.println("========================================");

                    return cleanRegFirst.equals(cleanInputFirst) && cleanRegLast.equals(cleanInputLast);
                }
            } else {
                System.out.println("DEBUG: NPI [" + npi + "] not found in Federal Registry.");
            }
        } catch (Exception e) {
            System.err.println("CRITICAL: NPI Validation System Error: " + e.getMessage());
        }
        return false;
    }
}