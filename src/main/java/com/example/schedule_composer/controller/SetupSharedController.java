package com.example.schedule_composer.controller;

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

import java.util.List;

@RestController
@RequestMapping(ApiConstants.SETUP_SHARED_API)
@Tag(name = "SetupShared API", description = "Endpoints for managing setup-shared relations, i.e. what course-teacher shared sets are there")
public class SetupSharedController {

    private final SetupSharedService setupSharedService;

    @Autowired
    public SetupSharedController(SetupSharedService setupSharedService) {
        this.setupSharedService = setupSharedService;
    }


    @GetMapping("/{setup_shared_id}")
    @Operation(summary = "Get setup-shared by ID", description = "Retrieves a specific setup-shared by its ID")
    public ResponseEntity<SetupSharedDTOGet> getById(
            @PathVariable("setup_shared_id") Long id) {
        SetupSharedDTOGet result = setupSharedService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all setup-shared", description = "Retrieves a list of all setup-shared's")
    public ResponseEntity<List<SetupSharedDTOGet>> getAll() {
        List<SetupSharedDTOGet> result = setupSharedService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/group-id/{groupId}")
    @Operation(summary = "Get all setup-shared entities by groupId", description = "Retrieves a list of all setup-shared's with specific groupId")
    public ResponseEntity<List<SetupSharedDTOGet>> getAllByGroupId(
            @PathVariable("groupId") Long groupId) {
        List<SetupSharedDTOGet> result = setupSharedService.getAllByGroupId(groupId);
        return ResponseEntity.ok(result);
    }


    @PostMapping()
    @Operation(summary = "Create setup-shared relation", description = "Creates new setup-shared relation")
    public ResponseEntity<SetupSharedDTOGet> create(
            @Valid @RequestBody SetupSharedDTOPost request) {
        SetupSharedDTOGet savedEntity = setupSharedService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{setup_shared_id}")
    @Operation(summary = "Update setup-shared relation", description = "Updates an existing setup-shared relation")
    public ResponseEntity<SetupSharedDTOGet> update(
            @PathVariable("setup_shared_id") Long id,
            @RequestBody SetupSharedDTOPatch patchRequest) {
        SetupSharedDTOGet updated = setupSharedService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{setup_shared_id}")
    @Operation(summary = "Delete setup-shared by ID", description = "Deletes a specific setup-shared by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("setup_shared_id") Long id) {
        setupSharedService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



//  ----  Shared groups ----
//
//
//
//    @GetMapping("/{setup_shared_id}/groups/{group_id}")
//    @Operation(summary = "Get shared-group of specific setup-shared by ID", description = "Retrieves a specific shared-group of specific setup-shared by its ID")
//    public ResponseEntity<GroupDTOGet> getById(
//            @PathVariable("setup_shared_id") Long setup_shared_id,
//            @PathVariable("group_id") Long group_id) {
//        GroupDTOGet result = setupSharedService.getGroupById(setup_shared_id, group_id);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/{setup_shared_id}/groups")
//    @Operation(summary = "Get all shared-group of specific setup-shared", description = "Retrieves a list of all shared-group of specific setup-shared")
//    public ResponseEntity<List<GroupDTOGet>> getAll(
//            @PathVariable("setup_shared_id") Long setup_shared_id) {
//        List<GroupDTOGet> result = setupSharedService.getAllGroups(setup_shared_id);
//        return ResponseEntity.ok(result);
//    }
//
//
//    @PostMapping("/{setup_shared_id}/groups")
//    @Operation(summary = "Add new shared-group to specific setup-shared", description = "Creates new shared-group of specific setup-shared")
//    public ResponseEntity<GroupDTOGet> create(
//            Long group_id,
//            @PathVariable("setup_shared_id") Long setup_shared_id) {
//        GroupDTOGet savedEntity = setupSharedService.addGroup(setup_shared_id, group_id);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
//    }
//
//    @DeleteMapping("/{setup_shared_id}/groups/{group_id}")
//    @Operation(summary = "Delete shared-group of specific setup-shared by ID", description = "Deletes a specific shared-group of specific setup-shared by its ID")
//    public ResponseEntity<Void> deleteGroupById(
//            @PathVariable("setup_shared_id") Long setup_shared_id,
//            @PathVariable("group_id") Long group_id) {
//        setupSharedService.deleteGroupById(setup_shared_id, group_id);
//        return ResponseEntity.noContent().build();
//    }
}
