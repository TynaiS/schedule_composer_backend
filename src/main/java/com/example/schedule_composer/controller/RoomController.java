package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    @Operation(summary = "Get all rooms", description = "Retrieves a list of all rooms")
    public List<RoomDTOGet> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{roomId}")
    @Operation(summary = "Get room by id", description = "Retrieves a room by id")
    public RoomDTOGet getRoomById(@PathVariable("roomId") Long id) {
        return roomService.getRoomById(id);
    }

}
