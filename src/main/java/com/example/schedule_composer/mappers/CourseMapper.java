package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;

import java.util.List;


public interface CourseMapper{
    CourseDTOGet fromEntityToGet(Course course);
    List<CourseDTOGet> fromEntityListToGetList(List<Course> courses);
    Course fromPostToEntity(CourseDTOPost courseDTOPost);
    Course fromPatchToEntity(CourseDTOPatch courseDTOPatch, Course courseToUpdate);
}
