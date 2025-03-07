package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.CourseTeacherSharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.service.CourseTeacherSharedGroupService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.COURSE_TEACHER_SHARED_GROUP_API)
@Tag(name = "CourseTeacherSharedGroup API", description = "Endpoints for managing course-teacher-shared-groups relations, i.e. groups that are assigned to specific course-teacher-shared")
public class CourseTeacherSharedGroupController {

    private final CourseTeacherSharedGroupService courseTeacherSharedGroupService;

    @Autowired
    public CourseTeacherSharedGroupController(CourseTeacherSharedGroupService courseTeacherSharedGroupService) {
        this.courseTeacherSharedGroupService = courseTeacherSharedGroupService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get course_teacher_shared by ID", description = "Retrieves a specific course_teacher_shared by its ID")
    public ResponseEntity<CourseTeacherSharedGroupDTOGet> getById(
            @PathVariable("id") Long id) {
        CourseTeacherSharedGroupDTOGet result = courseTeacherSharedGroupService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all course-teacher-shared-groups", description = "Retrieves a list of all course-teacher-shared-groups")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<CourseTeacherSharedGroupDTOGet>> getAll() {
        List<CourseTeacherSharedGroupDTOGet> result = courseTeacherSharedGroupService.getAll();
        return ResponseEntity.ok(result);
    }


    @PostMapping()
    @Operation(summary = "Create course-teacher-shared-groups relation", description = "Creates new course-teacher-shared-groups relation")
    public ResponseEntity<CourseTeacherSharedGroupDTOGet> create(
            @Valid @RequestBody CourseTeacherSharedGroupDTOPost request) {
        CourseTeacherSharedGroupDTOGet savedEntity = courseTeacherSharedGroupService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update course-teacher-shared-groups relation", description = "Updates an existing course-teacher-shared-groups relation")
    public ResponseEntity<CourseTeacherSharedGroupDTOGet> update(
            @PathVariable Long id,
            @RequestBody CourseTeacherSharedGroupDTOPatch patchRequest) {
        CourseTeacherSharedGroupDTOGet updated = courseTeacherSharedGroupService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course-teacher-shared-groups by ID", description = "Deletes a specific course-teacher-shared-groups by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        courseTeacherSharedGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
