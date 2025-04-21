package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.SetupSharedNameDTOGet;
import com.example.schedule_composer.dto.mappers.impl.SetupSharedNameMapper;
import com.example.schedule_composer.dto.patch.SetupSharedNameDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedNameDTOPost;
import com.example.schedule_composer.entity.SetupSharedName;
import com.example.schedule_composer.repository.SetupSharedNameRepository;
import com.example.schedule_composer.service.SetupSharedNameService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetupSharedNameServiceImpl implements SetupSharedNameService {

    private final SetupSharedNameRepository groupRepository;
    private final SetupSharedNameMapper groupMapper;

    @Autowired
    public SetupSharedNameServiceImpl(SetupSharedNameRepository groupRepository,SetupSharedNameMapper groupMapper){
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public SetupSharedNameDTOGet getById(Long id) {
        return groupMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupSharedName getEntityById(Long id) {
        SetupSharedName entity = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SetupSharedName not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupSharedName not found with id: " + id);
        }
        return true;
    }

    @Override
    public SetupSharedName checkIfExistsAndGetEntity(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("SetupSharedName not found with id: " + id);
        }
        return getEntityById(id);
    }

    @Override
    public List<SetupSharedName> checkIfAllExistAndGetEntities(List<Long> groupIds) {
        return groupIds.stream()
                .map(this::checkIfExistsAndGetEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SetupSharedNameDTOGet> getAll() {
        List<SetupSharedName> entities = groupRepository.findAll();

        return groupMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<SetupSharedName> getAllEntities() {
        return groupRepository.findAll();
    }

    @Override
    public SetupSharedNameDTOGet create(SetupSharedNameDTOPost createDto) {
        SetupSharedName savedEntity = groupRepository.save(groupMapper.fromPostToEntity(createDto));
        return groupMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupSharedNameDTOGet update(Long id, SetupSharedNameDTOPatch updateDto) {
        SetupSharedName updatedEntity = groupRepository.save(groupMapper.fromPatchToEntity(updateDto, id));
        return groupMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) groupRepository.deleteById(id);
    }
}
