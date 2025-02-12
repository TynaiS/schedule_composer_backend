package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.CourseGroupTeacherPatchRequest;
import com.example.schedule_composer.dto.CourseGroupTeacherPostRequest;
import com.example.schedule_composer.entity.CourseGroupTeacher;
import com.example.schedule_composer.service.CourseGroupTeacherService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.COURSE_GROUP_TEACHER_API)
@Tag(name = "CourseGroupTeacher API", description = "Endpoints for managing course-group-teacher relations, i.e. what courses and teachers assigned for specific groups")
public class CourseGroupTeacherController {

    private final CourseGroupTeacherService courseGroupService;

    @Autowired
    public CourseGroupTeacherController(CourseGroupTeacherService courseGroupService) {
        this.courseGroupService = courseGroupService;
    }

    @GetMapping()
    @Operation(summary = "Get all course_group_teacher", description = "Retrieves a list of all course_group_teacher's")
    public List<CourseGroupTeacher> getCourseGroupTeachers() {
        System.out.println(courseGroupService.getCourseGroupTeachers());
        return courseGroupService.getCourseGroupTeachers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course_group_teacher by ID", description = "Retrieves a specific course_group_teacher by its ID")
    public CourseGroupTeacher getCourseGroupTeacherById(
            @PathVariable Long id) {
        System.out.println(courseGroupService.getCourseGroupTeacherById(id) .getTeacher());
        return courseGroupService.getCourseGroupTeacherById(id);
    }

    @PostMapping
    @Operation(summary = "Create course_group_teacher relation", description = "Creates new course_group_teacher relation when you add specific course to group")
    public ResponseEntity<CourseGroupTeacher> createCourseGroupTeacher(
            @RequestBody CourseGroupTeacherPostRequest request) {
        CourseGroupTeacher savedEntity = courseGroupService.createCourseGroupTeacher(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PostMapping("/generate")
    @Operation(summary = "Generates the schedule", description = "Generated the schedule")
    public ResponseEntity<String> generateSchedule() {
        return ResponseEntity.ok("Schedule created");
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update course_group_teacher relation", description = "Updates an existing course_group_teacher relation")
    public ResponseEntity<CourseGroupTeacher> patchCourseGroupTeacher(
            @PathVariable Long id,
            @RequestBody CourseGroupTeacherPatchRequest patchRequest) {
        CourseGroupTeacher updated = courseGroupService.updateCourseGroupTeacher(id, patchRequest);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course_group_teacher by ID", description = "Deletes a specific course_group_teacher by its ID")
    public ResponseEntity<Void> deleteCourseGroupTeacher(@PathVariable Long id) {
        courseGroupService.deleteCourseGroupTeacher(id);
        return ResponseEntity.noContent().build();
    }

}
