package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.utils.ApiConstants;
import com.example.schedule_composer.utils.CoursePriority;
import com.example.schedule_composer.utils.TeachingMode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.COURSE_API)
@Tag(name = "Course API", description = "Endpoints for managing student courses ")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieves a specific course by its ID")
    public ResponseEntity<CourseDTOGet> getById(@PathVariable("id") Long id) {
        CourseDTOGet course = courseService.getById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping()
    @Operation(summary = "Get all courses", description = "Retrieves a list of all courses")
    public ResponseEntity<List<CourseDTOGet>>  getAll() {
        List<CourseDTOGet> courses = courseService.getAll();
        return ResponseEntity.ok(courses);
    }

    @PostMapping()
    @Operation(summary = "Create course", description = "Creates new course")
    public ResponseEntity<CourseDTOGet> create(
            @Valid @RequestBody CourseDTOPost request) {
        CourseDTOGet savedEntity = courseService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update course", description = "Updates an existing course")
    public ResponseEntity<CourseDTOGet> update(
            @PathVariable Long id,
            @RequestBody CourseDTOPatch patchRequest) {
        CourseDTOGet updated = courseService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course by ID", description = "Deletes a specific course by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course-priorities")
    public ResponseEntity<List<CoursePriority>> getCoursePriorities() {
        return ResponseEntity.ok(List.of(CoursePriority.values()));
    }

    @GetMapping("/teaching-modes")
    public ResponseEntity<List<TeachingMode>> getTeachingModes() {
        return ResponseEntity.ok(List.of(TeachingMode.values()));
    }
}
