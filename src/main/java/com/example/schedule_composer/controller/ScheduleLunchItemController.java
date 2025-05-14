package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.service.ScheduleLunchItemService;
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
@RequestMapping(ApiConstants.SCHEDULE_LUNCH_ITEM_API)
@Tag(name = "ScheduleLunchItem API", description = "Endpoints for managing schedule-lunch items")
public class ScheduleLunchItemController {


    private final ScheduleLunchItemService scheduleLunchItemService;

    @Autowired
    public ScheduleLunchItemController(ScheduleLunchItemService scheduleLunchItemService) {
        this.scheduleLunchItemService = scheduleLunchItemService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule-lunch item by ID", description = "Retrieves a specific schedule-lunch item by its ID")
    public ResponseEntity<ScheduleLunchItemDTOGet> getById(@PathVariable("id") Long id) {
        ScheduleLunchItemDTOGet scheduleLunchItem = scheduleLunchItemService.getById(id);
        return ResponseEntity.ok(scheduleLunchItem);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule-lunch items", description = "Retrieves a list of all schedule-lunch items")
    public ResponseEntity<List<ScheduleLunchItemDTOGet>> getAll() {
        List<ScheduleLunchItemDTOGet> scheduleLunches = scheduleLunchItemService.getAll();
        return ResponseEntity.ok(scheduleLunches);
    }

    @PostMapping()
    @Operation(summary = "Create schedule-lunch item", description = "Creates new schedule-lunch item")
    public ResponseEntity<ScheduleLunchItemDTOGet> create(
            @Valid @RequestBody ScheduleLunchItemDTOPost request) {
        ScheduleLunchItemDTOGet savedEntity = scheduleLunchItemService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule-lunch item", description = "Updates an existing schedule-lunch item")
    public ResponseEntity<ScheduleLunchItemDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleLunchItemDTOPatch patchRequest) {
        ScheduleLunchItemDTOGet updated = scheduleLunchItemService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule-lunch item by ID", description = "Deletes a specific schedule-lunch item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleLunchItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
