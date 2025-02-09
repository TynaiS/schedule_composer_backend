package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.CourseGroupTeacherPostRequest;
import com.example.schedule_composer.entity.CourseGroupTeacher;
import com.example.schedule_composer.repository.CourseGroupTeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseGroupTeacherService {

    private final CourseGroupTeacherRepository courseGroupTeacherRepository;

    @Autowired
    public CourseGroupTeacherService(CourseGroupTeacherRepository courseGroupTeacherRepository) {
        this.courseGroupTeacherRepository = courseGroupTeacherRepository;
    }

    public List<CourseGroupTeacher> getCourseGroupTeachers() {
        return courseGroupTeacherRepository.findAll();
    }

    public CourseGroupTeacher getCourseGroupTeacherById(Long id) {
        return courseGroupTeacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CourseGroupTeacher not found with id " + id));
    }

    public CourseGroupTeacher createCourseGroupTeacher(CourseGroupTeacher request) {
        return courseGroupTeacherRepository.save(request);
    }

    public void deleteCourseGroupTeacher(Long id) {
        courseGroupTeacherRepository.deleteById(id);
    }
}
