package com.example.schedule_composer.controller;

import com.example.schedule_composer.utils.ApiConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.GENERATOR_API)
public class GeneratorController {
    @PostMapping("/generate")
    public ResponseEntity<String> generateSchedule() {
        return ResponseEntity.ok("Schedule generated successfully!");
    }

}
