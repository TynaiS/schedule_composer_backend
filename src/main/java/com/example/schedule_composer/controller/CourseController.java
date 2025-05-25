package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.User;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.utils.ApiConstants;
import com.example.schedule_composer.utils.types.CoursePriority;
import com.example.schedule_composer.utils.types.TeachingMode;
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
@RequestMapping(ApiConstants.COURSE_API)
@Tag(name = "Course API", description = "Endpoints for managing student Courses inside of Schedule")
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/{courseId}")
    @Operation(summary = "Get Course by ID", description = "Retrieves a specific Course by its ID for Schedule")
    public ResponseEntity<CourseDTOGet> getById(
            @AuthenticationPrincipal User user,
            @PathVariable("courseId") Long courseId) {
        Long userId = user.getId();
        CourseDTOGet course = courseService.getByIdForUserSchedule(userId, courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/forSchedule/{scheduleId}")
    @Operation(summary = "Get all Courses", description = "Retrieves a list of all Courses for Schedule")
    public ResponseEntity<List<CourseDTOGet>>  getAll(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId) {
        Long userId = user.getId();
        List<CourseDTOGet> courses = courseService.getAllForUserSchedule(userId, scheduleId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/forSchedule/{scheduleId}")
    @Operation(summary = "Create Course", description = "Creates new Course for Schedule")
    public ResponseEntity<CourseDTOGet> create(
            @AuthenticationPrincipal User user,
            @PathVariable("scheduleId") Long scheduleId,
            @Valid @RequestBody CourseDTOPost request) {
        Long userId = user.getId();
        CourseDTOGet savedEntity = courseService.createForUserSchedule(userId, scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{courseId}")
    @Operation(summary = "Update Course", description = "Updates an existing Course for Schedule")
    public ResponseEntity<CourseDTOGet> update(
            @AuthenticationPrincipal User user,
            @PathVariable("courseId") Long courseId,
            @Valid @RequestBody CourseDTOPatch patchRequest) {
        Long userId = user.getId();
        CourseDTOGet updated = courseService.updateForUserSchedule(userId, courseId, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{courseId}")
    @Operation(summary = "Delete Course by ID", description = "Deletes a specific Course by its ID for Schedule")
    public ResponseEntity<Void> deleteById(
            @AuthenticationPrincipal User user,
            @PathVariable("courseId") Long courseId) {
        Long userId = user.getId();
        courseService.deleteByIdForUserSchedule(userId, courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course-priorities")
    @Operation(summary = "Get Course priorities", description = "Retrieves all possible Course priorities")
    public ResponseEntity<List<CoursePriority>> getCoursePriorities() {
        return ResponseEntity.ok(List.of(CoursePriority.values()));
    }

    @GetMapping("/teaching-modes")
    @Operation(summary = "Get teaching modes", description = "Retrieves all possible teaching modes")
    public ResponseEntity<List<TeachingMode>> getTeachingModes() {
        return ResponseEntity.ok(List.of(TeachingMode.values()));
    }
}
