package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedDTOGet;
import com.example.schedule_composer.dto.mappers.impl.ScheduleSharedMapper;
import com.example.schedule_composer.dto.patch.ScheduleSharedDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedDTOPost;
import com.example.schedule_composer.entity.ScheduleShared;
import com.example.schedule_composer.repository.ScheduleSharedRepository;
import com.example.schedule_composer.service.ScheduleSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleSharedServiceImpl implements ScheduleSharedService {

    private final ScheduleSharedRepository scheduleSharedRepository;
    private final ScheduleSharedMapper scheduleSharedMapper;

    @Autowired
    public ScheduleSharedServiceImpl(ScheduleSharedRepository scheduleSharedRepository,ScheduleSharedMapper scheduleSharedMapper){
        this.scheduleSharedRepository = scheduleSharedRepository;
        this.scheduleSharedMapper = scheduleSharedMapper;
    }

    @Override
    public ScheduleSharedDTOGet getById(Long id) {
        return scheduleSharedMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleShared getEntityById(Long id) {
        ScheduleShared entity = scheduleSharedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSharedRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleSharedDTOGet> getAll() {
        List<ScheduleShared> entities = scheduleSharedRepository.findAll();

        return scheduleSharedMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<ScheduleShared> getAllEntities() {
        return scheduleSharedRepository.findAll();
    }

    @Override
    public ScheduleSharedDTOGet create(ScheduleSharedDTOPost createDto) {
        ScheduleShared savedEntity = scheduleSharedRepository.save(scheduleSharedMapper.fromPostToEntity(createDto));
        return scheduleSharedMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedDTOGet create(ScheduleShared createEntity) {
        ScheduleShared savedEntity = scheduleSharedRepository.save(createEntity);
        return scheduleSharedMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedDTOGet update(Long id, ScheduleSharedDTOPatch updateDto) {
        ScheduleShared updatedEntity = scheduleSharedRepository.save(scheduleSharedMapper.fromPatchToEntity(updateDto, id));
        return scheduleSharedMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSharedRepository.deleteById(id);
    }
}
