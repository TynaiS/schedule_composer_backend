package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.ScheduleVersion;
import com.example.schedule_composer.entity.SetupSharedSet;
import com.example.schedule_composer.mappers.SetupSharedSetMapper;
import com.example.schedule_composer.repository.SetupSharedSetRepository;
import com.example.schedule_composer.service.ScheduleVersionService;
import com.example.schedule_composer.service.SetupSharedSetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SetupSharedSetServiceImpl implements SetupSharedSetService {

    private final SetupSharedSetRepository setupSharedSetRepository;
    private final SetupSharedSetMapper setupSharedSetMapper;
    private final ScheduleVersionService scheduleVersionService;

    @Override
    public SetupSharedSetDTOGet getById(Long id) {
        return setupSharedSetMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupSharedSet getEntityById(Long id) {
        SetupSharedSet entity = setupSharedSetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SetupSharedSet not found with id: " + id));
        return entity;
    }

    @Override
    public SetupSharedSetDTOGet create(SetupSharedSetDTOPost createDto) {
        SetupSharedSet savedEntity = setupSharedSetRepository.save(setupSharedSetMapper.fromPostToEntity(createDto));
        return setupSharedSetMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupSharedSetDTOGet update(Long setupSharedSetId, SetupSharedSetDTOPatch updateDto) {
        SetupSharedSet existing = getEntityById(setupSharedSetId);
        SetupSharedSet updatedEntity = setupSharedSetRepository.save(setupSharedSetMapper.fromPatchToEntity(updateDto, existing));
        return setupSharedSetMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) setupSharedSetRepository.deleteById(id);
    }

    @Override
    public List<SetupSharedSetDTOGet> getAll() {
        List<SetupSharedSet> entities = setupSharedSetRepository.findAll();

        return setupSharedSetMapper.fromEntityListToGetList(entities);
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!setupSharedSetRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupSharedSet not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupSharedSet> checkIfAllExistAndGetEntities(List<Long> setupSharedSetIds) {
        return setupSharedSetIds.stream()
                .map(this::getEntityById)
                .collect(Collectors.toList());
    }

    @Override
    public List<SetupSharedSet> getAllEntities() {
        return setupSharedSetRepository.findAll();
    }

    @Override
    public SetupSharedSetDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId) {
        return setupSharedSetMapper.fromEntityToGet(getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId));
    }

    @Override
    public List<SetupSharedSetDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId) {
        return setupSharedSetMapper.fromEntityListToGetList(getAllEntitiesForUserScheduleVersion(userId, scheduleId, scheduleVersionId));
    }

    @Override
    public SetupSharedSetDTOGet createForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, SetupSharedSetDTOPost request) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId);

        SetupSharedSet setupSharedSet = setupSharedSetMapper.fromPostToEntity(request);
        setupSharedSet.setScheduleVersion(scheduleVersion);
        return setupSharedSetMapper.fromEntityToGet(setupSharedSetRepository.save(setupSharedSet));
    }

    @Override
    public SetupSharedSetDTOGet updateForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId, SetupSharedSetDTOPatch patchRequest) {
        SetupSharedSet setupSharedSet = getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId);

        setupSharedSet = setupSharedSetMapper.fromPatchToEntity(patchRequest, setupSharedSet);
        return setupSharedSetMapper.fromEntityToGet(setupSharedSetRepository.save(setupSharedSet));
    }

    @Override
    public void deleteByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId) {
        SetupSharedSet setupSharedSet = getEntityByIdForUserScheduleVersion(userId, scheduleId, scheduleVersionId, setupSharedSetId);
        setupSharedSetRepository.delete(setupSharedSet);
    }

    @Override
    public void checkSetupSharedSetId(SetupSharedSet setupSharedSet, Long setupSharedSetId, String entityName) {
        if (!setupSharedSet.getId().equals(setupSharedSetId)) {
            throw new IllegalArgumentException(entityName + " does not belong to the given SetupSharedSet");
        }
    }

    @Override
    public SetupSharedSet getEntityByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupSharedSetId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId);

        SetupSharedSet setupSharedSet = getEntityById(setupSharedSetId);
        scheduleVersionService.checkScheduleVersionId(scheduleVersion, setupSharedSet.getScheduleVersion().getId(), "SetupSharedSet");
        return setupSharedSet;
    }

    @Override
    public List<SetupSharedSet> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUserSchedule(userId, scheduleId, scheduleVersionId);

        return setupSharedSetRepository.findAllByScheduleVersionId(scheduleVersion.getId());

    }


}
