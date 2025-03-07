package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.patch.GroupCourseTeacherDTOPatch;
import com.example.schedule_composer.dto.post.CourseTeacherSharedDTOPost;
import com.example.schedule_composer.dto.post.CourseTeacherSharedGroupDTOPost;
import com.example.schedule_composer.dto.post.GroupCourseTeacherDTOPost;
import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.get.CourseTeacherSharedGroupDTOGet;
import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.service.GroupCourseTeacherService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.GROUP_COURSE_TEACHER_API)
@Tag(name = "GroupCourseTeacher API", description = "Endpoints for managing course-group-teacher relations, i.e. what courses and teachers assigned for specific groups")
public class GroupCourseTeacherController {

    private final GroupCourseTeacherService groupCourseTeacherService;

    @Autowired
    public GroupCourseTeacherController(GroupCourseTeacherService groupCourseTeacherService) {
        this.groupCourseTeacherService = groupCourseTeacherService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group-course-teacher by ID", description = "Retrieves a specific group-course-teacher by its ID")
    public ResponseEntity<GroupCourseTeacherDTOGet> getById(
            @PathVariable("id") Long id) {
        GroupCourseTeacherDTOGet result = groupCourseTeacherService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    @Operation(summary = "Get all group-course-teacher", description = "Retrieves a list of all group-course-teacher's")
    public ResponseEntity<List<GroupCourseTeacherDTOGet>> getAll() {
        List<GroupCourseTeacherDTOGet> result = groupCourseTeacherService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Create group-course-teacher relation", description = "Creates new group-course-teacher relation")
    public ResponseEntity<GroupCourseTeacherDTOGet> create(
            @Valid @RequestBody GroupCourseTeacherDTOPost request) {
        GroupCourseTeacherDTOGet savedEntity = groupCourseTeacherService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update group-course-teacher relation", description = "Updates an existing group-course-teacher relation")
    public ResponseEntity<GroupCourseTeacherDTOGet> update(
            @PathVariable Long id,
            @RequestBody GroupCourseTeacherDTOPatch patchRequest) {
        GroupCourseTeacherDTOGet updated = groupCourseTeacherService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete group-course-teacher by ID", description = "Deletes a specific group-course-teacher by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        groupCourseTeacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
