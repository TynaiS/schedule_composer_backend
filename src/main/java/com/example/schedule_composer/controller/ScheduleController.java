package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.ScheduleService;
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
@RequestMapping(ApiConstants.SCHEDULE_API)
@Tag(name = "Schedule API", description = "Endpoints for managing Schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @GetMapping("/{scheduleId}")
    @Operation(summary = "Get Schedule by ID", description = "Retrieves a specific Schedule by its ID")
    public ResponseEntity<ScheduleDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        ScheduleDTOGet scheduleItem = scheduleService.getByIdForUser(userId,scheduleId);
        return ResponseEntity.ok(scheduleItem);
    }

    @GetMapping("/owner")
    @Operation(summary = "Get all Schedules for owner", description = "Retrieves a list of all Schedules, where user is owner of the Schedule")
    public ResponseEntity<List<ScheduleDTOGet>> getAllForOwner(
            @AuthenticationPrincipal User user) {
        Long userId = user.getId();
        List<ScheduleDTOGet> schedule = scheduleService.getAllForUserOwner(userId);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/editor")
    @Operation(summary = "Get all Schedules for editor", description = "Retrieves a list of all Schedules, where user is editor of the Schedule")
    public ResponseEntity<List<ScheduleDTOGet>> getAllForEditor(
            @AuthenticationPrincipal User user) {
        Long userId = user.getId();
        List<ScheduleDTOGet> schedule = scheduleService.getAllForUserEditor(userId);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping()
    @Operation(summary = "Create Schedule", description = "Creates new Schedule")
    public ResponseEntity<ScheduleDTOGet> create(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ScheduleDTOPost request) {
        ScheduleDTOGet savedEntity = scheduleService.createForUser(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{scheduleId}")
    @Operation(summary = "Update Schedule", description = "Updates an existing Schedule")
    public ResponseEntity<ScheduleDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @RequestBody ScheduleDTOPatch patchRequest) {
        Long userId = user.getId();
        ScheduleDTOGet updated = scheduleService.updateForUser(userId, scheduleId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "Delete Schedule by ID", description = "Deletes a specific Schedule by its ID")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        scheduleService.deleteByIdForUser(userId, scheduleId);
        return ResponseEntity.noContent().build();
    }
}
