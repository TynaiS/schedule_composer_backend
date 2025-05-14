package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.service.ScheduleSharedItemService;
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
@RequestMapping(ApiConstants.SCHEDULE_SHARED_ITEM_API)
@Tag(name = "ScheduleSharedItem API", description = "Endpoints for managing schedule-shareds items")
public class ScheduleSharedItemController {


    private final ScheduleSharedItemService scheduleSharedItemService;

    @Autowired
    public ScheduleSharedItemController(ScheduleSharedItemService scheduleSharedItemService) {
        this.scheduleSharedItemService = scheduleSharedItemService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule-shared item by ID", description = "Retrieves a specific schedule-shared item by its ID")
    public ResponseEntity<ScheduleSharedItemDTOGet> getById(@PathVariable("id") Long id) {
        ScheduleSharedItemDTOGet result = scheduleSharedItemService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule-shared items", description = "Retrieves a list of all schedule-shared items")
    public ResponseEntity<List<ScheduleSharedItemDTOGet>> getAll() {
        List<ScheduleSharedItemDTOGet> result = scheduleSharedItemService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @Operation(summary = "Create schedule-shared item", description = "Creates new schedule-shared item")
    public ResponseEntity<ScheduleSharedItemDTOGet> create(
            @Valid @RequestBody ScheduleSharedItemDTOPost request) {
        ScheduleSharedItemDTOGet savedEntity = scheduleSharedItemService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule-shared item", description = "Updates an existing schedule-shared item")
    public ResponseEntity<ScheduleSharedItemDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleSharedItemDTOPatch patchRequest) {
        ScheduleSharedItemDTOGet updated = scheduleSharedItemService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule-shared item by ID", description = "Deletes a specific schedule-shared item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleSharedItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
