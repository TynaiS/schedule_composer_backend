package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.service.CourseTeacherSharedService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.COURSE_TEACHER_SHARED_API)
@Tag(name = "CourseTeacherShared API", description = "Endpoints for managing course-teacher-shared relations, i.e. what course-teacher shared sets are there")
public class CourseTeacherSharedController {

    private final CourseTeacherSharedService courseTeacherSharedService;

    @Autowired
    public CourseTeacherSharedController(CourseTeacherSharedService courseTeacherSharedService) {
        this.courseTeacherSharedService = courseTeacherSharedService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get course-teacher-shared by ID", description = "Retrieves a specific course-teacher-shared by its ID")
    public CourseTeacherSharedDTOGet getById(
            @PathVariable("id") Long id) {
        return courseTeacherSharedService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all course-teacher-shared", description = "Retrieves a list of all course-teacher-shared's")
    public List<CourseTeacherSharedDTOGet> getAll() {
        return courseTeacherSharedService.getAll();
    }


    @PostMapping()
    @Operation(summary = "Create course-teacher-shared relation", description = "Creates new course-teacher-shared relation")
    public ResponseEntity<CourseTeacherSharedDTOGet> create(
            @RequestBody CourseTeacherSharedDTOPost request) {
        CourseTeacherSharedDTOGet savedEntity = courseTeacherSharedService.create(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update course-teacher-shared relation", description = "Updates an existing course-teacher-shared relation")
    public ResponseEntity<CourseTeacherSharedDTOGet> update(
            @PathVariable Long id,
            @RequestBody CourseTeacherSharedDTOPatch patchRequest) {
        CourseTeacherSharedDTOGet updated = courseTeacherSharedService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course-teacher-shared by ID", description = "Deletes a specific course-teacher-shared by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        courseTeacherSharedService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
