package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleVersionDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleVersionDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleVersionDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.ScheduleVersion;
import com.example.schedule_composer.mappers.ScheduleVersionMapper;
import com.example.schedule_composer.repository.ScheduleVersionRepository;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.service.ScheduleVersionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleVersionServiceImpl implements ScheduleVersionService {

    private final ScheduleVersionRepository scheduleVersionRepository;
    private final ScheduleVersionMapper scheduleVersionMapper;
    private final ScheduleService scheduleService;

    @Override
    public ScheduleVersionDTOGet getById(Long id) {
        return scheduleVersionMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleVersionDTOGet create(ScheduleVersionDTOPost createDto) {
        ScheduleVersion savedEntity = scheduleVersionRepository.save(scheduleVersionMapper.fromPostToEntity(createDto));
        return scheduleVersionMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleVersionDTOGet create(ScheduleVersion createEntity) {
        ScheduleVersion savedEntity = scheduleVersionRepository.save(createEntity);
        return scheduleVersionMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleVersionDTOGet update(Long scheduleVersionId, ScheduleVersionDTOPatch updateDto) {
        ScheduleVersion existing = getEntityById(scheduleVersionId);
        ScheduleVersion updatedEntity = scheduleVersionRepository.save(scheduleVersionMapper.fromPatchToEntity(updateDto, existing));
        return scheduleVersionMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (checkIfExists(id)) scheduleVersionRepository.deleteById(id);
    }

    @Override
    public List<ScheduleVersionDTOGet> getAll() {
        List<ScheduleVersion> entities = scheduleVersionRepository.findAll();
        return scheduleVersionMapper.fromEntityListToGetList(entities);
    }

    @Override
    public ScheduleVersion getEntityById(Long id) {
        ScheduleVersion entity = scheduleVersionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleVersion not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleVersionRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleVersion not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleVersion> getAllEntities() {
        return scheduleVersionRepository.findAll();
    }

    @Override
    public ScheduleVersionDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId) {
        return scheduleVersionMapper.fromEntityToGet(getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId));
    }

    @Override
    public List<ScheduleVersionDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return scheduleVersionMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public ScheduleVersionDTOGet createForUserSchedule(Long userId, Long scheduleId, ScheduleVersionDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        ScheduleVersion scheduleVersion = scheduleVersionMapper.fromPostToEntity(request);
        scheduleVersion.setSchedule(schedule);
        ScheduleVersion saved = scheduleVersionRepository.save(scheduleVersion);
        return scheduleVersionMapper.fromEntityToGet(saved);
    }

    @Override
    public ScheduleVersionDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId, ScheduleVersionDTOPatch patchRequest) {
        ScheduleVersion scheduleVersion = getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId);

        scheduleVersion = scheduleVersionMapper.fromPatchToEntity(patchRequest, scheduleVersion);
        ScheduleVersion updatedScheduleVersion = scheduleVersionRepository.save(scheduleVersion);
        return scheduleVersionMapper.fromEntityToGet(updatedScheduleVersion);
    }

    @Override
    public void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId);

        scheduleVersionRepository.delete(scheduleVersion);
    }

    @Override
    public void checkScheduleVersionId(ScheduleVersion scheduleVersion, Long scheduleVersionId, String entityName) {
        if (!scheduleVersion.getId().equals(scheduleVersionId)) {
            throw new IllegalArgumentException(entityName + " does not belong to the given ScheduleVersion");
        }
    }

    @Override
    public ScheduleVersion getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        ScheduleVersion scheduleVersion = getEntityById(scheduleVersionId);
        scheduleService.checkScheduleId(schedule, scheduleVersion.getSchedule().getId(), "ScheduleVersion");
        return scheduleVersion;
    }

    @Override
    public List<ScheduleVersion> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return scheduleVersionRepository.findAllByScheduleId(schedule.getId());
    }
}
