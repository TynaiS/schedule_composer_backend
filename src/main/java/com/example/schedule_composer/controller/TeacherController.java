package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.dto.patch.TeacherDTOPatch;
import com.example.schedule_composer.dto.post.TeacherDTOPost;
import com.example.schedule_composer.service.TeacherService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.TEACHER_API)
@Tag(name = "Teacher API", description = "Endpoints for managing teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get teacher by ID", description = "Retrieves a specific teacher by its ID")
    public TeacherDTOGet getById(@PathVariable("id") Long id) {
        return teacherService.getById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers")
    public List<TeacherDTOGet> getAll() {
        System.out.println(teacherService.getAll());
        return teacherService.getAll();
    }

    @PostMapping()
    @Operation(summary = "Create teacher", description = "Creates new teacher")
    public ResponseEntity<TeacherDTOGet> create(
            @RequestBody TeacherDTOPost request) {
        TeacherDTOGet savedEntity = teacherService.create(request);
        return ResponseEntity.ok(savedEntity);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update teacher", description = "Updates an existing teacher")
    public ResponseEntity<TeacherDTOGet> update(
            @PathVariable Long id,
            @RequestBody TeacherDTOPatch patchRequest) {
        TeacherDTOGet updated = teacherService.update(id, patchRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete teacher by ID", description = "Deletes a specific teacher by its ID")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
