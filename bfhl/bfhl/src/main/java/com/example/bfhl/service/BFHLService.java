package com.example.bfhl.service;

import org.springframework.stereotype.Service;

import java.util.*;
// import java.util.stream.Collectors;

@Service
public class BFHLService {

    public Map<String, Object> processInput(Map<String, Object> request) {
        @SuppressWarnings("unchecked")
        List<String> data = (List<String>) request.get("data");
        String fileBase64 = (String) request.get("file_b64");

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> lowercase = new ArrayList<>();

        for (String item : data) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
                if (item.matches("[a-z]")) {
                    lowercase.add(item);
                }
            }
        }

        // Find highest lowercase alphabet
        String highestLowercase = lowercase.stream()
                                            .max(String::compareTo)
                                            .orElse(null);

        // Check if any number is prime
        boolean isPrimeFound = numbers.stream()
                .anyMatch(num -> isPrime(Integer.parseInt(num)));

        // File handling (Mocked here for simplicity)
        boolean fileValid = fileBase64 != null && !fileBase64.isEmpty();
        String fileMimeType = fileValid ? "mock/type" : null;
        String fileSizeKb = fileValid ? "123" : null;

        // Create Response
        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("user_id", "john_doe_17091999"); // Replace with dynamic user ID
        response.put("email", "john@xyz.com");
        response.put("roll_number", "ABCD123");
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", Collections.singletonList(highestLowercase));
        response.put("is_prime_found", isPrimeFound);
        response.put("file_valid", fileValid);
        response.put("file_mime_type", fileMimeType);
        response.put("file_size_kb", fileSizeKb);

        return response;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
