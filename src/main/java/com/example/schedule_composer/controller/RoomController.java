package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.utils.ApiConstants;
import com.example.schedule_composer.utils.RoomType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.ROOM_API)
@Tag(name = "Room API", description = "Endpoints for managing rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get room by ID", description = "Retrieves a specific room by its ID")
    public ResponseEntity<RoomDTOGet> getById(@PathVariable("id") Long id) {
        RoomDTOGet room = roomService.getById(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping()
    @Operation(summary = "Get all rooms", description = "Retrieves a list of all rooms")
    public ResponseEntity<List<RoomDTOGet>> getAll() {
        List<RoomDTOGet> rooms = roomService.getAll();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping()
    @Operation(summary = "Create room", description = "Creates new room")
    public ResponseEntity<RoomDTOGet> create(
            @Valid @RequestBody RoomDTOPost request) {
        RoomDTOGet savedEntity = roomService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update room", description = "Updates an existing room")
    public ResponseEntity<RoomDTOGet> update(
            @PathVariable Long id,
            @RequestBody RoomDTOPatch patchRequest) {
        RoomDTOGet updated = roomService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete room by ID", description = "Deletes a specific room by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/room-types")
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        return ResponseEntity.ok(List.of(RoomType.values()));
    }
}
