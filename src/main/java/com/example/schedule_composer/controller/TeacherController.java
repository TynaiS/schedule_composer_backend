package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.TeacherDTOGet;
import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.service.TeacherService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    @Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers")
    public List<TeacherDTOGet> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping("/{teacherId}")
    @Operation(summary = "Get teacher by id", description = "Retrieves a teacher by id")
    public TeacherDTOGet getTeacherById(@PathVariable("teacherId") Long id) {
        return teacherService.getTeacherById(id);
    }

}
