package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupDTOGet;
import com.example.schedule_composer.dto.mappers.SetupMapper;
import com.example.schedule_composer.dto.patch.SetupDTOPatch;
import com.example.schedule_composer.dto.post.SetupDTOPost;
import com.example.schedule_composer.entity.Setup;
import com.example.schedule_composer.repository.SetupRepository;
import com.example.schedule_composer.service.SetupService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetupServiceImpl implements SetupService {

    private final SetupRepository scheduleSetupRegularRepository;
    private final SetupMapper scheduleSetupRegularMapper;

    @Autowired
    public SetupServiceImpl(SetupRepository scheduleSetupRegularRepository,SetupMapper scheduleSetupRegularMapper){
        this.scheduleSetupRegularRepository = scheduleSetupRegularRepository;
        this.scheduleSetupRegularMapper = scheduleSetupRegularMapper;
    }

    @Override
    public SetupDTOGet getById(Long id) {
        return scheduleSetupRegularMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Setup getEntityById(Long id) {
        Setup entity = scheduleSetupRegularRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setup not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSetupRegularRepository.existsById(id)) {
            throw new EntityNotFoundException("Setup not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupDTOGet> getAll() {
        List<Setup> entities = scheduleSetupRegularRepository.findAll();

        return scheduleSetupRegularMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SetupDTOGet create(SetupDTOPost createDto) {
        Setup savedEntity = scheduleSetupRegularRepository.save(scheduleSetupRegularMapper.fromPostToEntity(createDto));
        return scheduleSetupRegularMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupDTOGet update(Long id, SetupDTOPatch updateDto) {
        Setup updatedEntity = scheduleSetupRegularRepository.save(scheduleSetupRegularMapper.fromPatchToEntity(updateDto, id));
        return scheduleSetupRegularMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSetupRegularRepository.deleteById(id);
    }
}
