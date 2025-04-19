package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.mappers.impl.ScheduleMapper;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.repository.ScheduleRepository;
import com.example.schedule_composer.service.ScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,ScheduleMapper scheduleMapper){
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public ScheduleDTOGet getById(Long id) {
        return scheduleMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Schedule getEntityById(Long id) {
        Schedule entity = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleDTOGet> getAll() {
        List<Schedule> entities = scheduleRepository.findAll();

        return scheduleMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<Schedule> getAllEntities() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleDTOGet create(ScheduleDTOPost createDto) {
        Schedule savedEntity = scheduleRepository.save(scheduleMapper.fromPostToEntity(createDto));
        return scheduleMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleDTOGet create(Schedule createEntity) {
        Schedule savedEntity = scheduleRepository.save(createEntity);
        return scheduleMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleDTOGet update(Long id, ScheduleDTOPatch updateDto) {
        Schedule updatedEntity = scheduleRepository.save(scheduleMapper.fromPatchToEntity(updateDto, id));
        return scheduleMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleRepository.deleteById(id);
    }
}
