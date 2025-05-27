package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.utils.ApiConstants;
import com.example.schedule_composer.utils.types.RoomType;
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
@RequestMapping(ApiConstants.ROOM_API)
@Tag(name = "Room API", description = "Endpoints for managing Rooms inside of Schedule")
public class RoomController {

    private final RoomService roomService;


    @GetMapping("/{roomId}")
    @Operation(summary = "Get Room by ID", description = "Retrieves a specific Room by its ID for Schedule")
    public ResponseEntity<RoomDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("roomId") Long roomId) {
        Long userId = user.getId();
        RoomDTOGet room = roomService.getByIdForUser(userId, roomId);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Get all Rooms", description = "Retrieves all Rooms for Schedule")
    public ResponseEntity<List<RoomDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        List<RoomDTOGet> rooms = roomService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Create Room", description = "Creates a new Room for Schedule")
    public ResponseEntity<RoomDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody RoomDTOPost request) {
        Long userId = user.getId();
        RoomDTOGet savedRoom = roomService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }


    @PatchMapping("/{roomId}")
    @Operation(summary = "Update Room", description = "Updates an existing Room for Schedule")
    public ResponseEntity<RoomDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @PathVariable("roomId") Long roomId,
            @Valid @RequestBody RoomDTOPatch patchRequest) {
        Long userId = user.getId();
        RoomDTOGet updatedRoom = roomService.updateForUser(userId, roomId, patchRequest);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{roomId}")
    @Operation(summary = "Delete Room", description = "Deletes a specific Room for Schedule")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("roomId") Long roomId) {
        Long userId = user.getId();
        roomService.deleteByIdForUser(userId, roomId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/room-types")
    @Operation(summary = "Get Room types", description = "Retrieves all possible Room types")
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        return ResponseEntity.ok(List.of(RoomType.values()));
    }
}
