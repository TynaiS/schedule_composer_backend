package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.mappers.SetupSharedMapper;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.entity.SetupShared;
import com.example.schedule_composer.repository.SetupSharedRepository;
import com.example.schedule_composer.service.SetupSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetupSharedServiceImpl implements SetupSharedService {

    private final SetupSharedRepository scheduleSetupSharedRepository;
    private final SetupSharedMapper scheduleSetupSharedMapper;

    @Autowired
    public SetupSharedServiceImpl(SetupSharedRepository scheduleSetupSharedRepository,SetupSharedMapper scheduleSetupSharedMapper){
        this.scheduleSetupSharedRepository = scheduleSetupSharedRepository;
        this.scheduleSetupSharedMapper = scheduleSetupSharedMapper;
    }

    @Override
    public SetupSharedDTOGet getById(Long id) {
        return scheduleSetupSharedMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupShared getEntityById(Long id) {
        SetupShared entity = scheduleSetupSharedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSetupSharedRepository.existsById(id)) {
            throw new EntityNotFoundException("Setup-Shared not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupSharedDTOGet> getAll() {
        List<SetupShared> entities = scheduleSetupSharedRepository.findAll();

        return scheduleSetupSharedMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SetupSharedDTOGet create(SetupSharedDTOPost createDto) {
        SetupShared savedEntity = scheduleSetupSharedRepository.save(scheduleSetupSharedMapper.fromPostToEntity(createDto));
        return scheduleSetupSharedMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupSharedDTOGet update(Long id, SetupSharedDTOPatch updateDto) {
        SetupShared updatedEntity = scheduleSetupSharedRepository.save(scheduleSetupSharedMapper.fromPatchToEntity(updateDto, id));
        return scheduleSetupSharedMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSetupSharedRepository.deleteById(id);
    }
}
