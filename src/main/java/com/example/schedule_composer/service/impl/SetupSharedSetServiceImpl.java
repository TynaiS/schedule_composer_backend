package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupSharedSetDTOGet;
import com.example.schedule_composer.mappers.impl.SetupSharedSetMapper;
import com.example.schedule_composer.dto.patch.SetupSharedSetDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedSetDTOPost;
import com.example.schedule_composer.entity.SetupSharedSet;
import com.example.schedule_composer.repository.SetupSharedSetRepository;
import com.example.schedule_composer.service.SetupSharedSetService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetupSharedSetServiceImpl implements SetupSharedSetService {

    private final SetupSharedSetRepository groupRepository;
    private final SetupSharedSetMapper groupMapper;

    @Autowired
    public SetupSharedSetServiceImpl(SetupSharedSetRepository groupRepository,SetupSharedSetMapper groupMapper){
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public SetupSharedSetDTOGet getById(Long id) {
        return groupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupSharedSet getEntityById(Long id) {
        SetupSharedSet entity = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SetupSharedSet not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupSharedSet not found with id: " + id);
        }
        return true;
    }

    @Override
    public SetupSharedSet checkIfExistsAndGetEntity(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupSharedSet not found with id: " + id);
        }
        return getEntityById(id);
    }

    @Override
    public List<SetupSharedSet> checkIfAllExistAndGetEntities(List<Long> groupIds) {
        return groupIds.stream()
                .map(this::checkIfExistsAndGetEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SetupSharedSetDTOGet> getAll() {
        List<SetupSharedSet> entities = groupRepository.findAll();

        return groupMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<SetupSharedSet> getAllEntities() {
        return groupRepository.findAll();
    }

    @Override
    public SetupSharedSetDTOGet create(SetupSharedSetDTOPost createDto) {
        SetupSharedSet savedEntity = groupRepository.save(groupMapper.fromPostToEntity(createDto));
        return groupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupSharedSetDTOGet update(Long id, SetupSharedSetDTOPatch updateDto) {
        SetupSharedSet updatedEntity = groupRepository.save(groupMapper.fromPatchToEntity(updateDto, id));
        return groupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) groupRepository.deleteById(id);
    }
}
