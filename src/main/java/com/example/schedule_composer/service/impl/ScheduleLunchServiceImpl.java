package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.mappers.ScheduleLunchMapper;
import com.example.schedule_composer.dto.patch.ScheduleLunchDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchDTOPost;
import com.example.schedule_composer.entity.ScheduleLunch;
import com.example.schedule_composer.repository.ScheduleLunchRepository;
import com.example.schedule_composer.service.ScheduleLunchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleLunchServiceImpl implements ScheduleLunchService {

    private final ScheduleLunchRepository scheduleLunchRepository;
    private final ScheduleLunchMapper scheduleLunchMapper;

    @Autowired
    public ScheduleLunchServiceImpl(ScheduleLunchRepository scheduleLunchRepository,ScheduleLunchMapper scheduleLunchMapper){
        this.scheduleLunchRepository = scheduleLunchRepository;
        this.scheduleLunchMapper = scheduleLunchMapper;
    }

    @Override
    public ScheduleLunchDTOGet getById(Long id) {
        return scheduleLunchMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleLunch getEntityById(Long id) {
        ScheduleLunch entity = scheduleLunchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule lunch item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleLunchRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule lunch item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleLunchDTOGet> getAll() {
        List<ScheduleLunch> entities = scheduleLunchRepository.findAll();

        return scheduleLunchMapper.fromEntityListToGetList(entities);
    }

    @Override
    public ScheduleLunchDTOGet create(ScheduleLunchDTOPost createDto) {
        ScheduleLunch savedEntity = scheduleLunchRepository.save(scheduleLunchMapper.fromPostToEntity(createDto));
        return scheduleLunchMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleLunchDTOGet update(Long id, ScheduleLunchDTOPatch updateDto) {
        ScheduleLunch updatedEntity = scheduleLunchRepository.save(scheduleLunchMapper.fromPatchToEntity(updateDto, id));
        return scheduleLunchMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleLunchRepository.deleteById(id);
    }
}
