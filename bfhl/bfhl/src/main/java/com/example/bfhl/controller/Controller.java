package com.example.bfhl.controller;

import com.example.bfhl.service.BFHLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class Controller {

    @Autowired
    private BFHLService service;

    @GetMapping
    public Map<String, Object> getOperationCode() {
        return Map.of("operation_code", 1);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> handlePost(@RequestBody Map<String, Object> request) {
        return service.processInput(request);
    }
}
