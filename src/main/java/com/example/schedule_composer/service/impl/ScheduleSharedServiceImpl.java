package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedDTOGet;
import com.example.schedule_composer.dto.mappers.ScheduleSharedMapper;
import com.example.schedule_composer.dto.patch.ScheduleSharedDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedDTOPost;
import com.example.schedule_composer.entity.ScheduleShared;
import com.example.schedule_composer.repository.ScheduleSharedRepository;
import com.example.schedule_composer.service.ScheduleSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleSharedServiceImpl implements ScheduleSharedService {

    private final ScheduleSharedRepository scheduleSharedCourseRepository;
    private final ScheduleSharedMapper scheduleSharedCourseMapper;

    @Autowired
    public ScheduleSharedServiceImpl(ScheduleSharedRepository scheduleSharedCourseRepository,ScheduleSharedMapper scheduleSharedCourseMapper){
        this.scheduleSharedCourseRepository = scheduleSharedCourseRepository;
        this.scheduleSharedCourseMapper = scheduleSharedCourseMapper;
    }

    @Override
    public ScheduleSharedDTOGet getById(Long id) {
        return scheduleSharedCourseMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleShared getEntityById(Long id) {
        ScheduleShared entity = scheduleSharedCourseRepository.findById(id)
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
    public List<ScheduleSharedDTOGet> getAll() {
        List<ScheduleShared> entities = scheduleSharedCourseRepository.findAll();

        return scheduleSharedCourseMapper.fromEntityListToGetList(entities);
    }

    @Override
    public ScheduleSharedDTOGet create(ScheduleSharedDTOPost createDto) {
        ScheduleShared savedEntity = scheduleSharedCourseRepository.save(scheduleSharedCourseMapper.fromPostToEntity(createDto));
        return scheduleSharedCourseMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedDTOGet update(Long id, ScheduleSharedDTOPatch updateDto) {
        ScheduleShared updatedEntity = scheduleSharedCourseRepository.save(scheduleSharedCourseMapper.fromPatchToEntity(updateDto, id));
        return scheduleSharedCourseMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSharedCourseRepository.deleteById(id);
    }
}
