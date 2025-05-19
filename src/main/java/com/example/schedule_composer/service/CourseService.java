package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.CourseDTOGet;
import com.example.schedule_composer.dto.patch.CourseDTOPatch;
import com.example.schedule_composer.dto.post.CourseDTOPost;
import com.example.schedule_composer.entity.Course;
import com.example.schedule_composer.entity.Schedule;

import java.util.List;

public interface CourseService{

    CourseDTOGet getById(Long id);
    CourseDTOGet create(CourseDTOPost createDto);
    CourseDTOGet update(Long id, CourseDTOPatch updateDto);
    void deleteById(Long id);
    List<CourseDTOGet> getAll();


    Course getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<Course> getAllEntities();


    CourseDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long courseId);
    List<CourseDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    CourseDTOGet createForUserSchedule(Long userId, Long scheduleId, CourseDTOPost request);
    CourseDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long courseId, CourseDTOPatch patchRequest);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long courseId);


    Course getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long courseId);
    List<Course> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
}
