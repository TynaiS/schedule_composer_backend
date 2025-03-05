package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedCourseDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedCourseDTOPost;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleSharedCourseService implements CrudService<ScheduleSharedCourseDTOGet, ScheduleSharedCourseDTOPost, ScheduleSharedCourseDTOPatch, Long> {
    @Override
    public ScheduleSharedCourseDTOGet getById(Long aLong) {
        return null;
    }

    @Override
    public List<ScheduleSharedCourseDTOGet> getAll() {
        return null;
    }

    @Override
    public ScheduleSharedCourseDTOGet create(ScheduleSharedCourseDTOPost createDto) {
        return null;
    }

    @Override
    public ScheduleSharedCourseDTOGet update(Long aLong, ScheduleSharedCourseDTOPatch updateDto) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
