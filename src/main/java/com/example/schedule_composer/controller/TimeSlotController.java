package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.service.TimeSlotService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.TIME_SLOT_API)
@Tag(name = "Time slot API", description = "Endpoints for managing time slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get time slot by ID", description = "Retrieves a specific time slot by its ID")
    public TimeSlotDTOGet getById(@PathVariable("id") Long id) {
        return timeSlotService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all time slots", description = "Retrieves a list of all time slots")
    public List<TimeSlotDTOGet> getAll() {
        System.out.println(timeSlotService.getAll());
        return timeSlotService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Create time slot", description = "Creates new time slot")
    public ResponseEntity<TimeSlotDTOGet> create(
            @RequestBody TimeSlotDTOPost request) {
        TimeSlotDTOGet savedEntity = timeSlotService.create(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update time slot", description = "Updates an existing time slot")
    public ResponseEntity<TimeSlotDTOGet> update(
            @PathVariable Long id,
            @RequestBody TimeSlotDTOPatch patchRequest) {
        TimeSlotDTOGet updated = timeSlotService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete time slot by ID", description = "Deletes a specific time slot by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        timeSlotService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
