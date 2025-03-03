package com.example.schedule_composer.controller;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.service.CourseService;
import com.example.schedule_composer.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    @Operation(summary = "Get all courses", description = "Retrieves a list of all student courses")
    public List<CourseDTOGet> getCourses() {
        System.out.println(courseService.getCourses());
        return courseService.getCourses();
    }

    @GetMapping("/{courseId}")
    @Operation(summary = "Get course by ID", description = "Retrieves a specific course by its ID")
    public CourseDTOGet getCourseById(@PathVariable("courseId") Long id) {
        return courseService.getCourseById(id);
    }

}
