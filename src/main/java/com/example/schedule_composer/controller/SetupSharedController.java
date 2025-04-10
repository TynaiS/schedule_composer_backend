package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.service.SetupSharedService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.SETUP_SHARED_API)
@Tag(name = "SetupShared API", description = "Endpoints for managing setup-shared relations, i.e. what course-teacher shared sets are there")
public class SetupSharedController {

    private final SetupSharedService scheduleSetupSharedService;

    @Autowired
    public SetupSharedController(SetupSharedService scheduleSetupSharedService) {
        this.scheduleSetupSharedService = scheduleSetupSharedService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get setup-shared by ID", description = "Retrieves a specific setup-shared by its ID")
    public ResponseEntity<SetupSharedDTOGet> getById(
            @PathVariable("id") Long id) {
        SetupSharedDTOGet result = scheduleSetupSharedService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all setup-shared", description = "Retrieves a list of all setup-shared's")
    public ResponseEntity<List<SetupSharedDTOGet>> getAll() {
        List<SetupSharedDTOGet> result = scheduleSetupSharedService.getAll();
        return ResponseEntity.ok(result);
    }


    @PostMapping()
    @Operation(summary = "Create setup-shared relation", description = "Creates new setup-shared relation")
    public ResponseEntity<SetupSharedDTOGet> create(
            @Valid @RequestBody SetupSharedDTOPost request) {
        SetupSharedDTOGet savedEntity = scheduleSetupSharedService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update setup-shared relation", description = "Updates an existing setup-shared relation")
    public ResponseEntity<SetupSharedDTOGet> update(
            @PathVariable Long id,
            @RequestBody SetupSharedDTOPatch patchRequest) {
        SetupSharedDTOGet updated = scheduleSetupSharedService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete setup-shared by ID", description = "Deletes a specific setup-shared by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleSetupSharedService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
