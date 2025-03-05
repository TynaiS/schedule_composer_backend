package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchDTOPost;
import com.example.schedule_composer.service.ScheduleLunchService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SCHEDULE_LUNCH_API)
@Tag(name = "Schedule Lunch API", description = "Endpoints for managing schedule lunch items")
public class ScheduleLunchController {


    private final ScheduleLunchService scheduleLunchService;

    @Autowired
    public ScheduleLunchController(ScheduleLunchService scheduleLunchService) {
        this.scheduleLunchService = scheduleLunchService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get schedule lunch item by ID", description = "Retrieves a specific schedule lunch item by its ID")
    public ScheduleLunchDTOGet getById(@PathVariable("id") Long id) {
        return scheduleLunchService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all schedule lunch items", description = "Retrieves a list of all schedule lunch items")
    public List<ScheduleLunchDTOGet> getAll() {
        System.out.println(scheduleLunchService.getAll());
        return scheduleLunchService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Create schedule lunch item", description = "Creates new schedule lunch item")
    public ResponseEntity<ScheduleLunchDTOGet> create(
            @RequestBody ScheduleLunchDTOPost request) {
        ScheduleLunchDTOGet savedEntity = scheduleLunchService.create(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update schedule lunch item", description = "Updates an existing schedule lunch item")
    public ResponseEntity<ScheduleLunchDTOGet> update(
            @PathVariable Long id,
            @RequestBody ScheduleLunchDTOPatch patchRequest) {
        ScheduleLunchDTOGet updated = scheduleLunchService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete schedule lunch item by ID", description = "Deletes a specific schedule lunch item by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleLunchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
