package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.mappers.ScheduleMapper;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService implements CrudService<ScheduleDTOGet, ScheduleDTOPost, ScheduleDTOPatch, Schedule, Long> {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository,ScheduleMapper scheduleMapper){
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

        return entities.stream()
                .map(scheduleMapper::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDTOGet create(ScheduleDTOPost createDto) {
        Schedule savedEntity = scheduleRepository.save(scheduleMapper.fromPostToEntity(createDto));
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
