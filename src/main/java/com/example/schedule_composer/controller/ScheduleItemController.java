package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.*;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.service.ScheduleItemService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SCHEDULE_ITEM_API)
@Tag(name = "ScheduleItem API", description = "Endpoints for managing schedule items")
public class ScheduleItemController {

    private final ScheduleItemService scheduleItemService;

    @Autowired
    public ScheduleItemController(ScheduleItemService scheduleItemService) {
        this.scheduleItemService = scheduleItemService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule item by ID", description = "Retrieves a specific schedule item by its ID")
    public ResponseEntity<ScheduleItemDTOGet> getById(@PathVariable("id") Long id) {
        ScheduleItemDTOGet scheduleItem = scheduleItemService.getById(id);
        return ResponseEntity.ok(scheduleItem);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule items", description = "Retrieves a list of all schedule items")
    public ResponseEntity<List<ScheduleItemDTOGet>> getAll() {
        List<ScheduleItemDTOGet> schedule = scheduleItemService.getAll();
        return ResponseEntity.ok(schedule);
    }

    @PostMapping()
    @Operation(summary = "Create schedule item", description = "Creates new schedule item")
    public ResponseEntity<ScheduleItemDTOGet> create(
            @Valid @RequestBody ScheduleItemDTOPost request) {
        ScheduleItemDTOGet savedEntity = scheduleItemService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule item", description = "Updates an existing schedule item")
    public ResponseEntity<ScheduleItemDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleItemDTOPatch patchRequest) {
        ScheduleItemDTOGet updated = scheduleItemService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule item by ID", description = "Deletes a specific schedule item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
