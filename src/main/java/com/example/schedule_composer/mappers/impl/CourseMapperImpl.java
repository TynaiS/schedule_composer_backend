package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.mappers.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTOGet fromEntityToGet(Course course) {
        CourseDTOGet courseGet = CourseDTOGet.builder()
                .id(course.getId())
                .scheduleId(course.getSchedule().getId())
                .name(course.getName())
                .credits(course.getCredits())
                .build();
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
    public Course fromPatchToEntity(CourseDTOPatch courseDTOPatch, Course courseToUpdate) {

        if (courseDTOPatch.getName() != null) {
            if (courseDTOPatch.getName().isBlank()) {
                throw new IllegalArgumentException("Course name cannot be blank");
            }
            courseToUpdate.setName(courseDTOPatch.getName());
        }

        if (courseDTOPatch.getCredits() != null) {
            courseToUpdate.setCredits(courseDTOPatch.getCredits());
        }

        return courseToUpdate;

    }
}
