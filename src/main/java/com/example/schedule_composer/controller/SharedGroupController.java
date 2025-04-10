package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.SharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.SharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.SharedGroupDTOPost;
import com.example.schedule_composer.service.SharedGroupService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SHARED_GROUP_API)
@Tag(name = "SharedGroup API", description = "Endpoints for managing shared-group relations, i.e. groups that are assigned to specific setup-shared")
public class SharedGroupController {

    private final SharedGroupService scheduleSetupSharedGroupService;

    @Autowired
    public SharedGroupController(SharedGroupService scheduleSetupSharedGroupService) {
        this.scheduleSetupSharedGroupService = scheduleSetupSharedGroupService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get shared-group by ID", description = "Retrieves a specific shared-group by its ID")
    public ResponseEntity<SharedGroupDTOGet> getById(
            @PathVariable("id") Long id) {
        SharedGroupDTOGet result = scheduleSetupSharedGroupService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all shared-group", description = "Retrieves a list of all shared-group")
    public ResponseEntity<List<SharedGroupDTOGet>> getAll() {
        List<SharedGroupDTOGet> result = scheduleSetupSharedGroupService.getAll();
        return ResponseEntity.ok(result);
    }


    @PostMapping()
    @Operation(summary = "Create shared-group relation", description = "Creates new shared-group relation")
    public ResponseEntity<SharedGroupDTOGet> create(
            @Valid @RequestBody SharedGroupDTOPost request) {
        SharedGroupDTOGet savedEntity = scheduleSetupSharedGroupService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update shared-group relation", description = "Updates an existing shared-group relation")
    public ResponseEntity<SharedGroupDTOGet> update(
            @PathVariable Long id,
            @RequestBody SharedGroupDTOPatch patchRequest) {
        SharedGroupDTOGet updated = scheduleSetupSharedGroupService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete shared-group by ID", description = "Deletes a specific shared-group by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        scheduleSetupSharedGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
