package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.service.SetupSharedSetService;
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
@RequestMapping(ApiConstants.SETUP_SHARED_SET_API)
@Tag(name = "SetupSharedSet API", description = "Endpoints for managing setupSharedSet")
public class SetupSharedSetController {

    private final SetupSharedSetService setupSharedSetService;

    @Autowired
    public SetupSharedSetController(SetupSharedSetService setupSharedSetService) {
        this.setupSharedSetService = setupSharedSetService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get setupSharedSet by ID", description = "Retrieves a specific student setupSharedSet by its ID")
    public ResponseEntity<SetupSharedSetDTOGet> getById(@PathVariable("id") Long id) {
        SetupSharedSetDTOGet setupSharedSet = setupSharedSetService.getById(id);
        return ResponseEntity.ok(setupSharedSet);
    }

    @GetMapping()
    @Operation(summary = "Get all setupSharedSets", description = "Retrieves a list of all student setupSharedSets")
    public ResponseEntity<List<SetupSharedSetDTOGet>> getAll() {
        List<SetupSharedSetDTOGet> setupSharedSets = setupSharedSetService.getAll();
        return ResponseEntity.ok(setupSharedSets);
    }

    @PostMapping()
    @Operation(summary = "Create setupSharedSet", description = "Creates new setupSharedSet")
    public ResponseEntity<SetupSharedSetDTOGet> create(
            @Valid @RequestBody SetupSharedSetDTOPost request) {
        SetupSharedSetDTOGet savedEntity = setupSharedSetService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update setupSharedSet", description = "Updates an existing setupSharedSet")
    public ResponseEntity<SetupSharedSetDTOGet> update(
            @PathVariable Long id,
            @RequestBody SetupSharedSetDTOPatch patchRequest) {
        SetupSharedSetDTOGet updated = setupSharedSetService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete setupSharedSet by ID", description = "Deletes a specific setupSharedSet by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        setupSharedSetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
