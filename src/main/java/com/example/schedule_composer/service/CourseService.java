package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTOGet> getCourses() {
//        return courseRepository.findAll();
        return null;
//        to be implemented;
    }

    public CourseDTOGet getCourseById(Long id) {
//        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + id));
        return null;
//        to be implemented;
    }
}
