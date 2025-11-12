package com.example.MiniProject.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilityService {

    public String generateEmployeeCode(String prefix) {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return prefix + uniqueId;
    }

    public String formatString(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return text.trim().toUpperCase();
    }
}