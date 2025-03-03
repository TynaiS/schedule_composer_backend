package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.ScheduleLunch;
import com.example.schedule_composer.entity.ScheduleSharedCourse;
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
    public List<ScheduleDTOGet> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "Get schedule item by id", description = "Retrieves a schedule item by id")
    public ScheduleDTOGet getScheduleById(@PathVariable("scheduleId") Long id) {
        return scheduleService.getScheduleById(id);
    }

//================================================================================
    @GetMapping("/shared_courses")
@   Operation(summary = "Get all schedule_shared_courses items", description = "Retrieves a list of all schedule_shared_courses items")
    public List<ScheduleSharedCourseDTOGet> getScheduleSharedCourses() {
        return scheduleService.getScheduleSharedCourses();
    }

    @GetMapping("/shared_courses/{scheduleSharedCourseId}")
    @Operation(summary = "Get schedule_shared_courses item by id", description = "Retrieves a schedule_shared_courses item by id")
    public ScheduleSharedCourseDTOGet getScheduleSharedCourseById(@PathVariable("scheduleSharedCourseId") Long id) {
        return scheduleService.getScheduleSharedCourseById(id);
    }

//================================================================================

    @GetMapping("/lunches")
    @Operation(summary = "Get all schedule lunch items", description = "Retrieves a list of all schedule lunch items")
    public List<ScheduleLunchDTOGet> getScheduleLunches() {
        return scheduleService.getScheduleLunches();
    }

    @GetMapping("/lunches/{scheduleLunchId}")
    @Operation(summary = "Get schedule lunch item by id", description = "Retrieves a schedule lunch item by id")
    public ScheduleLunchDTOGet getScheduleLunchById(@PathVariable("scheduleLunchId") Long id) {
        return scheduleService.getScheduleLunchById(id);
    }

//================================================================================

    @GetMapping("/timeslots")
    @Operation(summary = "Get all schedule timeslots", description = "Retrieves a list of all schedule timeslots")
    public List<TimeSlotDTOGet> getTimeslots() {
        return scheduleService.getTimeslots();
    }

    @GetMapping("/timeslots/{timeslotId}")
    @Operation(summary = "Get schedule timeslot by id", description = "Retrieves a schedule timeslot by id")
    public TimeSlotDTOGet getTimeslotById(@PathVariable("timeslotId") Long id) {
        return scheduleService.getTimeslotById(id);
    }
}
