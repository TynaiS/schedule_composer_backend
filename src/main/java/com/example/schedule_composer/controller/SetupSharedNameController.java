package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.SetupSharedNameDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedNameDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedNameDTOPost;
import com.example.schedule_composer.service.SetupSharedNameService;
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
@RequestMapping(ApiConstants.SETUP_SHARED_NAME_API)
@Tag(name = "SetupSharedName API", description = "Endpoints for managing student setupSharedNames")
public class SetupSharedNameController {

    private final SetupSharedNameService setupSharedNameService;

    @Autowired
    public SetupSharedNameController(SetupSharedNameService setupSharedNameService) {
        this.setupSharedNameService = setupSharedNameService;
    }



    @GetMapping("/{id}")
    @Operation(summary = "Get setupSharedName by ID", description = "Retrieves a specific student setupSharedName by its ID")
    public ResponseEntity<SetupSharedNameDTOGet> getById(@PathVariable("id") Long id) {
        SetupSharedNameDTOGet setupSharedName = setupSharedNameService.getById(id);
        return ResponseEntity.ok(setupSharedName);
    }

    @GetMapping()
    @Operation(summary = "Get all setupSharedNames", description = "Retrieves a list of all student setupSharedNames")
    public ResponseEntity<List<SetupSharedNameDTOGet>> getAll() {
        List<SetupSharedNameDTOGet> setupSharedNames = setupSharedNameService.getAll();
        return ResponseEntity.ok(setupSharedNames);
    }

    @PostMapping()
    @Operation(summary = "Create setupSharedName", description = "Creates new setupSharedName")
    public ResponseEntity<SetupSharedNameDTOGet> create(
            @Valid @RequestBody SetupSharedNameDTOPost request) {
        SetupSharedNameDTOGet savedEntity = setupSharedNameService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update setupSharedName", description = "Updates an existing setupSharedName")
    public ResponseEntity<SetupSharedNameDTOGet> update(
            @PathVariable Long id,
            @RequestBody SetupSharedNameDTOPatch patchRequest) {
        SetupSharedNameDTOGet updated = setupSharedNameService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete setupSharedName by ID", description = "Deletes a specific setupSharedName by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        setupSharedNameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
