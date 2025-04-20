package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.patch.SetupDTOPatch;
import com.example.schedule_composer.dto.post.SetupDTOPost;
import com.example.schedule_composer.dto.get.SetupDTOGet;
import com.example.schedule_composer.service.SetupService;
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
@RequestMapping(ApiConstants.SETUP_API)
@Tag(name = "Setup API", description = "Endpoints for managing setup relations, i.e. what courses and teachers assigned for specific groups")
public class SetupController {

    private final SetupService setupService;

    @Autowired
    public SetupController(SetupService setupService) {
        this.setupService = setupService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get setup by ID", description = "Retrieves a specific setup by its ID")
    public ResponseEntity<SetupDTOGet> getById(
            @PathVariable("id") Long id) {
        SetupDTOGet result = setupService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all setup", description = "Retrieves a list of all setups")
    public ResponseEntity<List<SetupDTOGet>> getAll() {
        List<SetupDTOGet> result = setupService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/group-id/{groupId}")
    @Operation(summary = "Get all setup entities by groupId", description = "Retrieves a list of all setups with specific groupId")
    public ResponseEntity<List<SetupDTOGet>> getAllByGroupId(
            @PathVariable("groupId") Long groupId) {
        List<SetupDTOGet> result = setupService.getAllByGroupId(groupId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Create setup relation", description = "Creates new setup relation")
    public ResponseEntity<SetupDTOGet> create(
            @Valid @RequestBody SetupDTOPost request) {
        SetupDTOGet savedEntity = setupService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update setup relation", description = "Updates an existing setup relation")
    public ResponseEntity<SetupDTOGet> update(
            @PathVariable Long id,
            @RequestBody SetupDTOPatch patchRequest) {
        SetupDTOGet updated = setupService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete setup by ID", description = "Deletes a specific setup by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        setupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
