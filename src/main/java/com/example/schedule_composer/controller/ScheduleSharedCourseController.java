package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedCourseDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedCourseDTOPost;
import com.example.schedule_composer.service.ScheduleSharedCourseService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SCHEDULE_SHARED_COURSE_API)
@Tag(name = "ScheduleSharedCourses API", description = "Endpoints for managing schedule shared courses items")
public class ScheduleSharedCourseController {


    private final ScheduleSharedCourseService scheduleSharedCourseService;

    @Autowired
    public ScheduleSharedCourseController(ScheduleSharedCourseService scheduleSharedCourseService) {
        this.scheduleSharedCourseService = scheduleSharedCourseService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule shared course item by ID", description = "Retrieves a specific schedule shared course item by its ID")
    public ScheduleSharedCourseDTOGet getById(@PathVariable("id") Long id) {
        return scheduleSharedCourseService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule shared course items", description = "Retrieves a list of all schedule shared course items")
    public List<ScheduleSharedCourseDTOGet> getAll() {
        System.out.println(scheduleSharedCourseService.getAll());
        return scheduleSharedCourseService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Create schedule shared course item", description = "Creates new schedule shared course item")
    public ResponseEntity<ScheduleSharedCourseDTOGet> create(
            @RequestBody ScheduleSharedCourseDTOPost request) {
        ScheduleSharedCourseDTOGet savedEntity = scheduleSharedCourseService.create(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule shared course item", description = "Updates an existing schedule shared course item")
    public ResponseEntity<ScheduleSharedCourseDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleSharedCourseDTOPatch patchRequest) {
        ScheduleSharedCourseDTOGet updated = scheduleSharedCourseService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule shared course item by ID", description = "Deletes a specific schedule shared course item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleSharedCourseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
