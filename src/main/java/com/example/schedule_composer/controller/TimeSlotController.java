package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.TimeSlotService;
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
@RequestMapping(ApiConstants.TIME_SLOT_API)
@Tag(name = "TimeSlot API", description = "Endpoints for managing TimeSlots inside of Schedule")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;


    @GetMapping("/{timeSlotId}")
    @Operation(summary = "Get TimeSlot by ID", description = "Retrieves a specific TimeSlot by its ID for Schedule")
    public ResponseEntity<TimeSlotDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable Long timeSlotId) {
        Long userId = user.getId();
        TimeSlotDTOGet timeSlot = timeSlotService.getByIdForUserSchedule(userId, timeSlotId);
        return ResponseEntity.ok(timeSlot);
    }

    @GetMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Get all TimeSlots", description = "Retrieves all TimeSlots for Schedule")
    public ResponseEntity<List<TimeSlotDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleId) {
        Long userId = user.getId();
        List<TimeSlotDTOGet> timeSlots = timeSlotService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(timeSlots);
    }

    @PostMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Create TimeSlot", description = "Creates a new TimeSlot for Schedule")
    public ResponseEntity<TimeSlotDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable Long scheduleId,
            @Valid @RequestBody TimeSlotDTOPost request) {
        Long userId = user.getId();
        TimeSlotDTOGet created = timeSlotService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{timeSlotId}")
    @Operation(summary = "Update TimeSlot", description = "Updates an existing TimeSlot for Schedule")
    public ResponseEntity<TimeSlotDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable Long timeSlotId,
            @Valid @RequestBody TimeSlotDTOPatch request) {
        Long userId = user.getId();
        TimeSlotDTOGet updated = timeSlotService.updateForUserSchedule(userId, timeSlotId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{timeSlotId}")
    @Operation(summary = "Delete TimeSlot", description = "Deletes a specific TimeSlot for Schedule")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal User user,
            @PathVariable Long timeSlotId) {
        Long userId = user.getId();
        timeSlotService.deleteByIdForUserSchedule(userId, timeSlotId);
        return ResponseEntity.noContent().build();
    }
}
