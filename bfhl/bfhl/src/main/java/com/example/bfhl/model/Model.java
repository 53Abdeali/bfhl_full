package com.example.bfhl.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Model {
    private boolean isSuccess;
    private String userId;
    private String email;
    private String rollNumber;
    private List<String> numbers;
    private List<String> alphabets;
    private List<String> highestLowercaseAlphabet;
    private boolean isPrimeFound;
    private boolean fileValid;
    private String fileMimeType;
    private String fileSizeKb;
}
