package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.mappers.ScheduleSharedCourseMapper;
import com.example.schedule_composer.dto.patch.ScheduleSharedCourseDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedCourseDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedCourse;
import com.example.schedule_composer.repository.ScheduleSharedCourseRepository;
import com.example.schedule_composer.service.ScheduleSharedCourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleSharedCourseServiceImpl implements ScheduleSharedCourseService {

    private final ScheduleSharedCourseRepository scheduleSharedCourseRepository;
    private final ScheduleSharedCourseMapper scheduleSharedCourseMapper;

    @Autowired
    public ScheduleSharedCourseServiceImpl(ScheduleSharedCourseRepository scheduleSharedCourseRepository,ScheduleSharedCourseMapper scheduleSharedCourseMapper){
        this.scheduleSharedCourseRepository = scheduleSharedCourseRepository;
        this.scheduleSharedCourseMapper = scheduleSharedCourseMapper;
    }

    @Override
    public ScheduleSharedCourseDTOGet getById(Long id) {
        return scheduleSharedCourseMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleSharedCourse getEntityById(Long id) {
        ScheduleSharedCourse entity = scheduleSharedCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSharedCourseRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleSharedCourseDTOGet> getAll() {
        List<ScheduleSharedCourse> entities = scheduleSharedCourseRepository.findAll();

        return scheduleSharedCourseMapper.fromEntityListToGetList(entities);
    }

    @Override
    public ScheduleSharedCourseDTOGet create(ScheduleSharedCourseDTOPost createDto) {
        ScheduleSharedCourse savedEntity = scheduleSharedCourseRepository.save(scheduleSharedCourseMapper.fromPostToEntity(createDto));
        return scheduleSharedCourseMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedCourseDTOGet update(Long id, ScheduleSharedCourseDTOPatch updateDto) {
        ScheduleSharedCourse updatedEntity = scheduleSharedCourseRepository.save(scheduleSharedCourseMapper.fromPatchToEntity(updateDto, id));
        return scheduleSharedCourseMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSharedCourseRepository.deleteById(id);
    }
}
