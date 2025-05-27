package com.example.schedule_composer.controller;

import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.schedule_generator.Generator;
import com.example.schedule_composer.utils.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.GENERATOR_API)
public class GeneratorController {

    private final Generator generator;
    @PostMapping("/generate/{scheduleVersionId}")
    public ResponseEntity<String> generateSchedule(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId) {
        generator.generate(user.getId(), scheduleVersionId);
        return ResponseEntity.ok("Schedule generated successfully!");
    }

}
