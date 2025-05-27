package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.DepartmentDTOGet;
import com.example.schedule_composer.dto.patch.DepartmentDTOPatch;
import com.example.schedule_composer.dto.post.DepartmentDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.DepartmentService;
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
@RequestMapping(ApiConstants.DEPARTMENT_API)
@Tag(name = "Department API", description = "Endpoints for managing student Departments inside of Schedule")
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping("/{departmentId}")
    @Operation(summary = "Get Department by ID", description = "Retrieves a specific Department by its ID for Schedule")
    public ResponseEntity<DepartmentDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("departmentId") Long departmentId) {
        Long userId = user.getId();
        DepartmentDTOGet department = departmentService.getByIdForUser(userId, departmentId);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Get all Departments", description = "Retrieves a list of all student Departments for Schedule")
    public ResponseEntity<List<DepartmentDTOGet>> getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        List<DepartmentDTOGet> departments = departmentService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/for-schedule/{scheduleId}")
    @Operation(summary = "Create Department", description = "Creates new Department for Schedule")
    public ResponseEntity<DepartmentDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody DepartmentDTOPost request) {
        Long userId = user.getId();
        DepartmentDTOGet savedEntity = departmentService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{departmentId}")
    @Operation(summary = "Update Department", description = "Updates an existing Department for Schedule")
    public ResponseEntity<DepartmentDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("departmentId") Long departmentId,
            @Valid @RequestBody DepartmentDTOPatch patchRequest) {
        Long userId = user.getId();
        DepartmentDTOGet updated = departmentService.updateForUser(userId,departmentId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{departmentId}")
    @Operation(summary = "Delete Department by ID", description = "Deletes a specific Department by its ID for Schedule")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("departmentId") Long departmentId) {
        Long userId = user.getId();
        departmentService.deleteByIdForUser(userId, departmentId);
        return ResponseEntity.noContent().build();
    }

}
