package com.example.schedule_composer.controller;

import com.example.schedule_composer.entity.Teacher;
import com.example.schedule_composer.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@Tag(name = "Teacher API", description = "Endpoints for managing teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public List<Teacher> getTeachers() {
        System.out.println(teacherService.getTeachers());
        return teacherService.getTeachers();
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") Long id) {
        return teacherService.getTeacherById(id);
    }

}
