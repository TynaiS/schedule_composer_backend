package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.SetupSharedSetService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.SETUP_SHARED_SET_API)
@Tag(name = "SetupSharedSet API", description = "Endpoints for managing SetupSharedSets of ScheduleVersion of Schedule")
public class SetupSharedSetController {

    private final SetupSharedSetService setupSharedSetService;


    @GetMapping("/{setupSharedSetId}")
    @Operation(summary = "Get SetupSharedSet by ID", description = "Retrieves a specific student SetupSharedSet by its ID for ScheduleVersion")
    public ResponseEntity<SetupSharedSetDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("setupSharedSetId") Long setupSharedSetId) {
        Long userId = user.getId();
        SetupSharedSetDTOGet setupSharedSet = setupSharedSetService.getByIdForUserScheduleVersion(userId, setupSharedSetId);
        return ResponseEntity.ok(setupSharedSet);
    }

    @GetMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Get all SetupSharedSets", description = "Retrieves a list of all student SetupSharedSets for ScheduleVersion")
    public ResponseEntity<List<SetupSharedSetDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId) {
        Long userId = user.getId();
        List<SetupSharedSetDTOGet> setupSharedSets = setupSharedSetService.getAllForUserScheduleVersion(userId, scheduleVersionId);
        return ResponseEntity.ok(setupSharedSets);
    }

    @PostMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Create SetupSharedSet", description = "Creates new SetupSharedSet for ScheduleVersion")
    public ResponseEntity<SetupSharedSetDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @Valid @RequestBody SetupSharedSetDTOPost request) {
        Long userId = user.getId();
        SetupSharedSetDTOGet savedEntity = setupSharedSetService.createForUserScheduleVersion(userId, scheduleVersionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{setupSharedSetId}")
    @Operation(summary = "Update SetupSharedSet", description = "Updates an existing SetupSharedSet for ScheduleVersion")
    public ResponseEntity<SetupSharedSetDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("setupSharedSetId") Long setupSharedSetId,
            @Valid @RequestBody SetupSharedSetDTOPatch patchRequest) {
        Long userId = user.getId();
        SetupSharedSetDTOGet updated = setupSharedSetService.updateForUserScheduleVersion(userId, setupSharedSetId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{setupSharedSetId}")
    @Operation(summary = "Delete SetupSharedSet by ID", description = "Deletes a specific SetupSharedSet by its ID for ScheduleVersion")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("setupSharedSetId") Long setupSharedSetId) {
        Long userId = user.getId();
        setupSharedSetService.deleteByIdForUserScheduleVersion(userId, setupSharedSetId);
        return ResponseEntity.noContent().build();
    }

}
