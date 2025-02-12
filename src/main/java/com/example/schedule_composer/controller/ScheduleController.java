package com.example.schedule_composer.controller;

import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SCHEDULE_API)
@Tag(name = "Schedule API", description = "Endpoints for managing schedule items")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping()
    @Operation(summary = "Get all schedule items", description = "Retrieves a list of all schedule items")
    public List<Schedule> getSchedules() {
        System.out.println(scheduleService.getSchedules());
        return scheduleService.getSchedules();
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "Get schedule item by id", description = "Retrieves a schedule item by id")
    public Schedule getScheduleById(@PathVariable("scheduleId") Long id) {
        return scheduleService.getScheduleById(id);
    }

}
