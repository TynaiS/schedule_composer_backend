package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.mappers.DTOMapper;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper implements DTOMapper<CourseDTOGet, CourseDTOPost, CourseDTOPatch, Course, Long> {

    private final CourseRepository courseRepository;

    @Override
    public CourseDTOGet fromEntityToGet(Course course) {
        CourseDTOGet courseGet = new CourseDTOGet(course.getId(), course.getName(), course.getCredits());
        return courseGet;
    }

    @Override
    public List<CourseDTOGet> fromEntityListToGetList(List<Course> courses) {
        return courses.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Course fromPostToEntity(CourseDTOPost courseDTOPost) {
        Course course = Course.builder()
                .name(courseDTOPost.getName())
                .credits(courseDTOPost.getCredits())
                .build();
        return course;
    }

    @Override
    public Course fromPatchToEntity(CourseDTOPatch courseDTOPatch, Long courseId) {

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id:" + courseId));

        if (courseDTOPatch.getName() != null) {
            if (courseDTOPatch.getName().isBlank()) {
                throw new IllegalArgumentException("Course name cannot be blank");
            }
            existingCourse.setName(courseDTOPatch.getName());
        }

        if (courseDTOPatch.getCredits() != null) {
            existingCourse.setCredits(courseDTOPatch.getCredits());
        }

        return existingCourse;

    }
}
