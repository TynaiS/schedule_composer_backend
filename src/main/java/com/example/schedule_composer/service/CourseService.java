package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;

import java.util.List;

public interface CourseService{

    CourseDTOGet getById(Long id);

    Course getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<CourseDTOGet> getAll();

    CourseDTOGet create(CourseDTOPost createDto);

    CourseDTOGet update(Long id, CourseDTOPatch updateDto);

    void deleteById(Long id);
}
