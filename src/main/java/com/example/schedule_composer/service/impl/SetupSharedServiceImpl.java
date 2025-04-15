package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.get.SetupSharedDTOGet;
import com.example.schedule_composer.dto.mappers.GroupMapper;
import com.example.schedule_composer.dto.mappers.SetupSharedMapper;
import com.example.schedule_composer.dto.patch.SetupSharedDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.SetupShared;
import com.example.schedule_composer.repository.SetupSharedRepository;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.SetupSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetupSharedServiceImpl implements SetupSharedService {

    private final SetupSharedRepository setupSharedRepository;
    private final SetupSharedMapper setupSharedMapper;

    @Autowired
    public SetupSharedServiceImpl(SetupSharedRepository setupSharedRepository,SetupSharedMapper setupSharedMapper){
        this.setupSharedRepository = setupSharedRepository;
        this.setupSharedMapper = setupSharedMapper;
    }

    @Override
    public SetupSharedDTOGet getById(Long id) {
        return setupSharedMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public SetupShared getEntityById(Long id) {
        SetupShared entity = setupSharedRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!setupSharedRepository.existsById(id)) {
            throw new EntityNotFoundException("Setup-Shared not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<SetupSharedDTOGet> getAll() {
        List<SetupShared> entities = setupSharedRepository.findAll();

        return setupSharedMapper.fromEntityListToGetList(entities);
    }

    @Override
    public SetupSharedDTOGet create(SetupSharedDTOPost createDto) {
        SetupShared savedEntity = setupSharedRepository.save(setupSharedMapper.fromPostToEntity(createDto));
        return setupSharedMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public SetupSharedDTOGet update(Long id, SetupSharedDTOPatch updateDto) {
        SetupShared updatedEntity = setupSharedRepository.save(setupSharedMapper.fromPatchToEntity(updateDto, id));
        return setupSharedMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) setupSharedRepository.deleteById(id);
    }


    //  ----  Shared groups ----
//
//    @Override
//    public GroupDTOGet getGroupById(Long setupSharedId, Long groupId) {
//        SetupShared setupShared = getEntityById(setupSharedId);
//        Group group = setupShared.getGroups().stream()
//                .filter(gr -> gr.getId().equals(groupId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("There is no group with id " + groupId + " in setup-shared with id " + setupSharedId));
//        return groupService.getById(group.getId());
//    }
//
//    @Override
//    public List<GroupDTOGet> getAllGroups(Long setupSharedId) {
//        SetupShared setupShared = getEntityById(setupSharedId);
//        return groupMapper.fromEntityListToGetList(setupShared.getGroups());
//    }
//
//    @Override
//    public GroupDTOGet addGroup(Long setupSharedId, Long groupId) {
//        SetupShared setupShared = getEntityById(setupSharedId);
//        boolean groupExists = setupShared.getGroups().stream()
//                .anyMatch(gr -> gr.getId().equals(groupId));
//
//        if (groupExists) {
//            throw new IllegalArgumentException("Group with id " + groupId + " already exists in setup-shared with id " + setupSharedId);
//        }
//
//        Group group = groupService.getEntityById(groupId);
//        setupShared.getGroups().add(group);
//        setupSharedRepository.save(setupShared);
//
//        return groupMapper.fromEntityToGet(group);
//    }
//
//    @Override
//    public void deleteGroupById(Long setupSharedId, Long groupId) {
//        SetupShared setupShared = getEntityById(setupSharedId);
//        Group group = setupShared.getGroups().stream()
//                .filter(gr -> gr.getId().equals(groupId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("There is no group with id " + groupId + " in setup-shared with id " + setupSharedId));
//
//        setupShared.getGroups().remove(group);
//        setupSharedRepository.save(setupShared);
//    }
}
