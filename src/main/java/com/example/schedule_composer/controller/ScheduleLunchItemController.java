package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.ScheduleLunchItemService;
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
@RequestMapping(ApiConstants.SCHEDULE_LUNCH_ITEM_API)
@Tag(name = "ScheduleLunchItem API", description = "Endpoints for managing schedule-lunch items")
public class ScheduleLunchItemController {

    private final ScheduleLunchItemService scheduleLunchItemService;

    @GetMapping("/{scheduleLunchItemId}")
    @Operation(summary = "Get ScheduleLunchItem by ID", description = "Retrieves a specific ScheduleLunchItem by its ID for ScheduleVersion")
    public ResponseEntity<ScheduleLunchItemDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleLunchItemId) {
        Long userId = user.getId();
        ScheduleLunchItemDTOGet lunchItem = scheduleLunchItemService.getByIdForUserScheduleVersion(userId, scheduleLunchItemId);
        return ResponseEntity.ok(lunchItem);
    }

    @GetMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Get all ScheduleLunchItems", description = "Retrieves a list of all ScheduleLunchItems for ScheduleVersion")
    public ResponseEntity<List<ScheduleLunchItemDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleVersionId) {
        Long userId = user.getId();
        List<ScheduleLunchItemDTOGet> items = scheduleLunchItemService.getAllForUserScheduleVersion(userId, scheduleVersionId);
        return ResponseEntity.ok(items);
    }


    @PostMapping("/for-schedule-version/{scheduleVersionId}")
    @Operation(summary = "Create ScheduleLunchItem", description = "Creates new ScheduleLunchItem for ScheduleVersion")
    public ResponseEntity<ScheduleLunchItemDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleVersionId,
            @Valid @RequestBody ScheduleLunchItemDTOPost request) {
        Long userId = user.getId();
        ScheduleLunchItemDTOGet created = scheduleLunchItemService.createForUserScheduleVersion(userId, scheduleVersionId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{scheduleLunchItemId}")
    @Operation(summary = "Update ScheduleLunchItem", description = "Updates an existing ScheduleLunchItem for ScheduleVersion")
    public ResponseEntity<ScheduleLunchItemDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleLunchItemId,
            @Valid @RequestBody ScheduleLunchItemDTOPatch patchRequest) {
        Long userId = user.getId();
        ScheduleLunchItemDTOGet updated = scheduleLunchItemService.updateForUserScheduleVersion(userId, scheduleLunchItemId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleLunchItemId}")
    @Operation(summary = "Delete ScheduleLunchItem by ID", description = "Deletes a specific ScheduleLunchItem by its ID for ScheduleVersion")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleLunchItemId) {
        Long userId = user.getId();
        scheduleLunchItemService.deleteByIdForUserScheduleVersion(userId, scheduleLunchItemId);
        return ResponseEntity.noContent().build();
    }
}
