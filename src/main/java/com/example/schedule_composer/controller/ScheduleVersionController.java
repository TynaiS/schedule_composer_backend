package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleVersionDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleVersionDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleVersionDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.ScheduleVersionService;
import com.example.schedule_composer.utils.ApiConstants;
import com.example.schedule_composer.utils.types.TeachingMode;
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
@RequestMapping(ApiConstants.SCHEDULE_VERSION_API)
@Tag(name = "ScheduleVersion API", description = "Endpoints for managing student ScheduleVersions of Schedule")
public class ScheduleVersionController {

    private final ScheduleVersionService scheduleVersionService;


    @GetMapping("/{scheduleVersionId}")
    @Operation(summary = "Get ScheduleVersion by ID", description = "Retrieves a specific ScheduleVersion by its ID for Schedule")
    public ResponseEntity<ScheduleVersionDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId) {
        Long userId = user.getId();
        ScheduleVersionDTOGet scheduleVersion = scheduleVersionService.getByIdForUser(userId, scheduleVersionId);
        return ResponseEntity.ok(scheduleVersion);
    }

    @GetMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Get all ScheduleVersions", description = "Retrieves a list of all ScheduleVersions for Schedule")
    public ResponseEntity<List<ScheduleVersionDTOGet>>  getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        List<ScheduleVersionDTOGet> scheduleVersions = scheduleVersionService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(scheduleVersions);
    }

    @PostMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Create ScheduleVersion", description = "Creates new ScheduleVersion for Schedule")
    public ResponseEntity<ScheduleVersionDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody ScheduleVersionDTOPost request) {
        Long userId = user.getId();
        ScheduleVersionDTOGet savedEntity = scheduleVersionService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{scheduleVersionId}")
    @Operation(summary = "Update ScheduleVersion", description = "Updates an existing ScheduleVersion for Schedule")
    public ResponseEntity<ScheduleVersionDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @Valid @RequestBody ScheduleVersionDTOPatch patchRequest) {
        Long userId = user.getId();
        ScheduleVersionDTOGet updated = scheduleVersionService.updateForUser(userId, scheduleVersionId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleVersionId}")
    @Operation(summary = "Delete ScheduleVersion by ID", description = "Deletes a specific ScheduleVersion by its ID for Schedule")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId) {
        Long userId = user.getId();
        scheduleVersionService.deleteByIdForUser(userId, scheduleVersionId);
        return ResponseEntity.noContent().build();
    }

}
