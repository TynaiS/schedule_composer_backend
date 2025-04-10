package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleSharedDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedDTOPost;
import com.example.schedule_composer.service.ScheduleSharedService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SCHEDULE_SHARED_API)
@Tag(name = "ScheduleShared API", description = "Endpoints for managing schedule-shared courses items")
public class ScheduleSharedController {


    private final ScheduleSharedService scheduleSharedCourseService;

    @Autowired
    public ScheduleSharedController(ScheduleSharedService scheduleSharedCourseService) {
        this.scheduleSharedCourseService = scheduleSharedCourseService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule-shared course item by ID", description = "Retrieves a specific schedule-shared course item by its ID")
    public ResponseEntity<ScheduleSharedDTOGet> getById(@PathVariable("id") Long id) {
        ScheduleSharedDTOGet result = scheduleSharedCourseService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule-shared course items", description = "Retrieves a list of all schedule-shared course items")
    public ResponseEntity<List<ScheduleSharedDTOGet>> getAll() {
        List<ScheduleSharedDTOGet> result = scheduleSharedCourseService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @Operation(summary = "Create schedule-shared course item", description = "Creates new schedule-shared course item")
    public ResponseEntity<ScheduleSharedDTOGet> create(
            @Valid @RequestBody ScheduleSharedDTOPost request) {
        ScheduleSharedDTOGet savedEntity = scheduleSharedCourseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule-shared course item", description = "Updates an existing schedule-shared course item")
    public ResponseEntity<ScheduleSharedDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleSharedDTOPatch patchRequest) {
        ScheduleSharedDTOGet updated = scheduleSharedCourseService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule-shared course item by ID", description = "Deletes a specific schedule-shared course item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleSharedCourseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
