package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.*;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.ScheduleItemService;
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
@RequestMapping(ApiConstants.SCHEDULE_ITEM_API)
@Tag(name = "ScheduleItem API", description = "Endpoints for managing ScheduleItems of ScheduleVersion of Schedule")
public class ScheduleItemController {

    private final ScheduleItemService scheduleItemService;



    @GetMapping("/{scheduleItemId}")
    @Operation(summary = "Get ScheduleItem by ID", description = "Retrieves a specific ScheduleItem by its ID for ScheduleVersion")
    public ResponseEntity<ScheduleItemDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleItemId") Long scheduleItemId) {
        Long userId = user.getId();
        ScheduleItemDTOGet scheduleItem = scheduleItemService.getByIdForUser(userId, scheduleItemId);
        return ResponseEntity.ok(scheduleItem);
    }

    @GetMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Get all ScheduleItems", description = "Retrieves a list of all ScheduleItems for ScheduleVersion")
    public ResponseEntity<List<ScheduleItemDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId) {
        Long userId = user.getId();
        List<ScheduleItemDTOGet> schedule = scheduleItemService.getAllForUserScheduleVersion(userId, scheduleVersionId);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Create ScheduleItem", description = "Creates new ScheduleItem for ScheduleVersion")
    public ResponseEntity<ScheduleItemDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleVersionId") Long scheduleVersionId,
            @Valid @RequestBody ScheduleItemDTOPost request) {
        Long userId = user.getId();
        ScheduleItemDTOGet savedEntity = scheduleItemService.createForUserScheduleVersion(userId, scheduleVersionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{scheduleItemId}")
    @Operation(summary = "Update ScheduleItem", description = "Updates an existing ScheduleItem for ScheduleVersion")
    public ResponseEntity<ScheduleItemDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleItemId") Long scheduleItemId,
            @Valid @RequestBody ScheduleItemDTOPatch patchRequest) {
        Long userId = user.getId();
        ScheduleItemDTOGet updated = scheduleItemService.updateForUser(userId, scheduleItemId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleItemId}")
    @Operation(summary = "Delete ScheduleItem by ID", description = "Deletes a specific ScheduleItem by its ID for ScheduleVersion")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleItemId") Long scheduleItemId) {
        Long userId = user.getId();
        scheduleItemService.deleteByIdForUser(userId, scheduleItemId);
        return ResponseEntity.noContent().build();
    }
}
