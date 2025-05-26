package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.ScheduleSharedItemService;
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
@RequestMapping(ApiConstants.SCHEDULE_SHARED_ITEM_API)
@Tag(name = "ScheduleSharedItem API", description = "Endpoints for managing ScheduleSharedItems of ScheduleVersion of Schedule")
public class ScheduleSharedItemController {

    private final ScheduleSharedItemService scheduleSharedItemService;


    @GetMapping("/{scheduleSharedItemId}")
    @Operation(summary = "Get ScheduleSharedItem by ID", description = "Retrieves a specific ScheduleSharedItem by its ID for ScheduleVersion")
    public ResponseEntity<ScheduleSharedItemDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleSharedItemId) {
        Long userId = user.getId();
        ScheduleSharedItemDTOGet item = scheduleSharedItemService.getByIdForUserScheduleVersion(userId, scheduleSharedItemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Get all ScheduleSharedItems", description = "Retrieves all ScheduleSharedItems for ScheduleVersion")
    public ResponseEntity<List<ScheduleSharedItemDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleVersionId) {
        Long userId = user.getId();
        List<ScheduleSharedItemDTOGet> items = scheduleSharedItemService.getAllForUserScheduleVersion(userId, scheduleVersionId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Create ScheduleSharedItem", description = "Creates a new ScheduleSharedItem for ScheduleVersion")
    public ResponseEntity<ScheduleSharedItemDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleVersionId,
            @Valid @RequestBody ScheduleSharedItemDTOPost request) {
        Long userId = user.getId();
        ScheduleSharedItemDTOGet created = scheduleSharedItemService.createForUserScheduleVersion(userId, scheduleVersionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{scheduleSharedItemId}")
    @Operation(summary = "Update ScheduleSharedItem", description = "Updates an existing ScheduleSharedItem for ScheduleVersion")
    public ResponseEntity<ScheduleSharedItemDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleSharedItemId,
            @Valid @RequestBody ScheduleSharedItemDTOPatch patchRequest) {
        Long userId = user.getId();
        ScheduleSharedItemDTOGet updated = scheduleSharedItemService.updateForUserScheduleVersion(userId, scheduleSharedItemId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleSharedItemId}")
    @Operation(summary = "Delete ScheduleSharedItem by ID", description = "Deletes a specific ScheduleSharedItem by its ID for ScheduleVersion")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleSharedItemId) {
        Long userId = user.getId();
        scheduleSharedItemService.deleteByIdForUserScheduleVersion(userId, scheduleSharedItemId);
        return ResponseEntity.noContent().build();
    }
}
