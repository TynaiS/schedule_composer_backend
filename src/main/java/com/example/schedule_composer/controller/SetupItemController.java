package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.patch.SetupItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupItemDTOPost;
import com.example.schedule_composer.dto.get.SetupItemDTOGet;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.SetupItemService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.SETUP_ITEM_API)
@Tag(name = "SetupItem API", description = "Endpoints for managing SetupItem relations of ScheduleVersion of Schedule")
public class SetupItemController {

    private final SetupItemService setupItemService;

    @GetMapping("/{setupItemId}")
    @Operation(summary = "Get SetupItem by ID", description = "Retrieves a specific SetupItem by its ID for ScheduleVersion")
    public ResponseEntity<SetupItemDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("setupItemId") Long setupItemId) {
        Long userId = user.getId();
        SetupItemDTOGet result = setupItemService.getByIdForUserScheduleVersion(userId, setupItemId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Get all SetupItem", description = "Retrieves a list of all SetupItems for ScheduleVersion")
    public ResponseEntity<List<SetupItemDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId) {
        Long userId = user.getId();
        List<SetupItemDTOGet> result = setupItemService.getAllForUserScheduleVersion(userId, scheduleVersionId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/for-schedule-version/{scheduleVersionId}/for-group/{groupId}")
    @Operation(summary = "Get all SetupItem entities by groupId", description = "Retrieves a list of all SetupItems with specific groupId for ScheduleVersion")
    public ResponseEntity<List<SetupItemDTOGet>> getAllByGroupId(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @PathVariable("groupId") Long groupId) {
        Long userId = user.getId();
        List<SetupItemDTOGet> result = setupItemService.getAllByGroupIdForUserScheduleVersion(userId, scheduleVersionId, groupId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Create SetupItem relation", description = "Creates new SetupItem relation for ScheduleVersion")
    public ResponseEntity<SetupItemDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @Valid @RequestBody SetupItemDTOPost request) {
        Long userId = user.getId();
        SetupItemDTOGet savedEntity = setupItemService.createForUserScheduleVersion(userId, scheduleVersionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{setupItemId}")
    @Operation(summary = "Update SetupItem relation", description = "Updates an existing SetupItem relation for ScheduleVersion")
    public ResponseEntity<SetupItemDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("setupItemId") Long setupItemId,
            @Valid @RequestBody SetupItemDTOPatch patchRequest) {
        Long userId = user.getId();
        SetupItemDTOGet updated = setupItemService.updateForUserScheduleVersion(userId, setupItemId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{setupItemId}")
    @Operation(summary = "Delete SetupItem by ID", description = "Deletes a specific SetupItem by its ID for ScheduleVersion")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("setupItemId") Long setupItemId) {
        Long userId = user.getId();
        setupItemService.deleteByIdForUserScheduleVersion(userId, setupItemId);
        return ResponseEntity.noContent().build();
    }
}
