package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SharedGroupDTOGet;
import com.example.schedule_composer.dto.mappers.SharedGroupMapper;
import com.example.schedule_composer.dto.patch.SharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.SharedGroupDTOPost;
import com.example.schedule_composer.entity.SharedGroup;
import com.example.schedule_composer.repository.SharedGroupRepository;
import com.example.schedule_composer.service.SharedGroupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SharedGroupServiceImpl implements SharedGroupService {

    private final SharedGroupRepository scheduleSetupSharedGroupRepository;
    private final SharedGroupMapper scheduleSetupSharedGroupMapper;

    @Autowired
    public SharedGroupServiceImpl(SharedGroupRepository scheduleSetupSharedGroupRepository,SharedGroupMapper scheduleSetupSharedGroupMapper){
        this.scheduleSetupSharedGroupRepository = scheduleSetupSharedGroupRepository;
        this.scheduleSetupSharedGroupMapper = scheduleSetupSharedGroupMapper;
    }

    @Override
    public SharedGroupDTOGet getById(Long id) {
        return scheduleSetupSharedGroupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SharedGroup getEntityById(Long id) {
        SharedGroup entity = scheduleSetupSharedGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared-Group not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSetupSharedGroupRepository.existsById(id)) {
            throw new EntityNotFoundException("Setup-Shared-Group not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SharedGroupDTOGet> getAll() {
        List<SharedGroup> entities = scheduleSetupSharedGroupRepository.findAll();

        return scheduleSetupSharedGroupMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SharedGroupDTOGet create(SharedGroupDTOPost createDto) {
        SharedGroup savedEntity = scheduleSetupSharedGroupRepository.save(scheduleSetupSharedGroupMapper.fromPostToEntity(createDto));
        return scheduleSetupSharedGroupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SharedGroupDTOGet update(Long id, SharedGroupDTOPatch updateDto) {
        SharedGroup updatedEntity = scheduleSetupSharedGroupRepository.save(scheduleSetupSharedGroupMapper.fromPatchToEntity(updateDto, id));
        return scheduleSetupSharedGroupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSetupSharedGroupRepository.deleteById(id);
    }
}
