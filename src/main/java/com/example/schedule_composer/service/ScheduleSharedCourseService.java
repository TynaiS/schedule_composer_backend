package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedCourseDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedCourseDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedCourse;

import java.util.List;

public interface ScheduleSharedCourseService {

    ScheduleSharedCourseDTOGet getById(Long id);

    ScheduleSharedCourse getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<ScheduleSharedCourseDTOGet> getAll();

    ScheduleSharedCourseDTOGet create(ScheduleSharedCourseDTOPost createDto);

    ScheduleSharedCourseDTOGet update(Long id, ScheduleSharedCourseDTOPatch updateDto);

    void deleteById(Long id);
}
