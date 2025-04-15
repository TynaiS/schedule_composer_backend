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

    private final SetupRepository setupRepository;
    private final SetupMapper setupMapper;

    @Autowired
    public SetupServiceImpl(SetupRepository setupRepository,SetupMapper setupMapper){
        this.setupRepository = setupRepository;
        this.setupMapper = setupMapper;
    }

    @Override
    public SetupDTOGet getById(Long id) {
        return setupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Setup getEntityById(Long id) {
        Setup entity = setupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setup not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!setupRepository.existsById(id)) {
            throw new EntityNotFoundException("Setup not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupDTOGet> getAll() {
        List<Setup> entities = setupRepository.findAll();

        return setupMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SetupDTOGet create(SetupDTOPost createDto) {
        Setup savedEntity = setupRepository.save(setupMapper.fromPostToEntity(createDto));
        return setupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupDTOGet update(Long id, SetupDTOPatch updateDto) {
        Setup updatedEntity = setupRepository.save(setupMapper.fromPatchToEntity(updateDto, id));
        return setupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) setupRepository.deleteById(id);
    }
}
