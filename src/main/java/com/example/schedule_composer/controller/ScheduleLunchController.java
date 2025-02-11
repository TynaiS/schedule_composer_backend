package com.example.schedule_composer.controller;

import com.example.schedule_composer.entity.ScheduleLunch;
import com.example.schedule_composer.service.ScheduleLunchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule_lunches")
@Tag(name = "Schedule Lunch API", description = "Endpoints for managing schedule lunches")
public class ScheduleLunchController {

    private final ScheduleLunchService scheduleLunchService;

    @Autowired
    public ScheduleLunchController(ScheduleLunchService scheduleLunchService) {
        this.scheduleLunchService = scheduleLunchService;
    }

    @GetMapping()
    @Operation(summary = "Get all schedule lunch items", description = "Retrieves a list of all schedule lunch items")
    public List<ScheduleLunch> getScheduleLunches() {
        System.out.println(scheduleLunchService.getScheduleLunches());
        return scheduleLunchService.getScheduleLunches();
    }

    @GetMapping("/{scheduleLunchId}")
    @Operation(summary = "Get schedule lunch item by id", description = "Retrieves a schedule lunch item by id")
    public ScheduleLunch getScheduleLunchById(@PathVariable("scheduleLunchId") Long id) {
        return scheduleLunchService.getScheduleLunchById(id);
    }

}
