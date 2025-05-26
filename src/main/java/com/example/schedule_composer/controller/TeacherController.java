package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.TeacherService;
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
@RequestMapping(ApiConstants.TEACHER_API)
@Tag(name = "Teacher API", description = "Endpoints for managing Teachers inside of Schedule")
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping("/{teacherId}")
    @Operation(summary = "Get Teacher by ID", description = "Retrieves a specific Teacher by its ID for Schedule")
    public ResponseEntity<TeacherDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("teacherId") Long teacherId) {
        Long userId = user.getId();
        TeacherDTOGet teacher = teacherService.getByIdForUser(userId, teacherId);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Get all Teachers", description = "Retrieves a list of all Teachers for Schedule")
    public ResponseEntity<List<TeacherDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        List<TeacherDTOGet> teachers = teacherService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(teachers);
    }

    @PostMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Create Teacher", description = "Creates new Teacher for Schedule")
    public ResponseEntity<TeacherDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody TeacherDTOPost request) {
        Long userId = user.getId();
        TeacherDTOGet savedEntity = teacherService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{teacherId}")
    @Operation(summary = "Update Teacher", description = "Updates an existing Teacher for Schedule")
    public ResponseEntity<TeacherDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("teacherId") Long teacherId,
            @Valid @RequestBody TeacherDTOPatch patchRequest) {
        Long userId = user.getId();
        TeacherDTOGet updated = teacherService.updateForUserSchedule(userId, teacherId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{teacherId}")
    @Operation(summary = "Delete Teacher by ID", description = "Deletes a specific Teacher by its ID for Schedule")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("teacherId") Long teacherId) {
        Long userId = user.getId();
        teacherService.deleteByIdForUserSchedule(userId, teacherId);
        return ResponseEntity.noContent().build();
    }

}
