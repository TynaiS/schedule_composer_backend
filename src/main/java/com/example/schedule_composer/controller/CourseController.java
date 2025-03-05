package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.COURSE_API)
@Tag(name = "Course API", description = "Endpoints for managing student courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieves a specific course by its ID")
    public CourseDTOGet getById(@PathVariable("id") Long id) {
        return courseService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all courses", description = "Retrieves a list of all courses")
    public List<CourseDTOGet> getAll() {
        System.out.println(courseService.getAll());
        return courseService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Create course", description = "Creates new course")
    public ResponseEntity<CourseDTOGet> create(
            @RequestBody CourseDTOPost request) {
        CourseDTOGet savedEntity = courseService.create(request);
        return ResponseEntity.ok(savedEntity);
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

}
