package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.*;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule item by ID", description = "Retrieves a specific schedule item by its ID")
    public ScheduleDTOGet getById(@PathVariable("id") Long id) {
        return scheduleService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule items", description = "Retrieves a list of all schedule items")
    public List<ScheduleDTOGet> getAll() {
        System.out.println(scheduleService.getAll());
        return scheduleService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Create schedule item", description = "Creates new schedule item")
    public ResponseEntity<ScheduleDTOGet> create(
            @RequestBody ScheduleDTOPost request) {
        ScheduleDTOGet savedEntity = scheduleService.create(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule item", description = "Updates an existing schedule item")
    public ResponseEntity<ScheduleDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleDTOPatch patchRequest) {
        ScheduleDTOGet updated = scheduleService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule item by ID", description = "Deletes a specific schedule item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
