package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper implements DTOMapper<CourseDTOGet, CourseDTOPost, CourseDTOPatch, Course, Long>{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseMapper(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @Override
    public CourseDTOGet fromEntityToGet(Course course) {
        CourseDTOGet courseGet = new CourseDTOGet(course.getId(), course.getName(), course.getCredits());
        return courseGet;
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
