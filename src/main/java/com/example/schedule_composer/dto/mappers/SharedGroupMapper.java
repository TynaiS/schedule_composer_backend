package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.SharedGroupDTOGet;
import com.example.schedule_composer.dto.patch.SharedGroupDTOPatch;
import com.example.schedule_composer.dto.post.SharedGroupDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.SharedGroupRepository;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.service.SetupSharedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SharedGroupMapper implements DTOMapper<SharedGroupDTOGet, SharedGroupDTOPost, SharedGroupDTOPatch, SharedGroup, Long>{

    private final SharedGroupRepository scheduleSetupSharedGroupRepository;
    private final GroupService groupService;
    private final SetupSharedService scheduleSetupSharedService;
    private final SetupSharedMapper scheduleSetupSharedMapper;

    @Autowired
    public SharedGroupMapper(
            SharedGroupRepository scheduleSetupSharedGroupRepository,
            GroupService groupService,
            SetupSharedService scheduleSetupSharedService,
            SetupSharedMapper scheduleSetupSharedMapper) {
        this.scheduleSetupSharedGroupRepository = scheduleSetupSharedGroupRepository;
        this.groupService = groupService;
        this.scheduleSetupSharedService = scheduleSetupSharedService;
        this.scheduleSetupSharedMapper = scheduleSetupSharedMapper;
    }

    @Override
    public SharedGroupDTOGet fromEntityToGet(SharedGroup scheduleSetupSharedGroup) {
        SharedGroupDTOGet scheduleSetupSharedGroupGet = new SharedGroupDTOGet(scheduleSetupSharedGroup.getId(), scheduleSetupSharedMapper.fromEntityToGet(scheduleSetupSharedGroup.getSetupShared()), scheduleSetupSharedGroup.getGroup());
        return scheduleSetupSharedGroupGet;
    }

    @Override
    public List<SharedGroupDTOGet> fromEntityListToGetList(List<SharedGroup> scheduleSetupSharedGroups) {
        return scheduleSetupSharedGroups.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public SharedGroup fromPostToEntity(SharedGroupDTOPost scheduleSetupSharedGroupDTOPost) {
        SetupShared scheduleSetupShared = scheduleSetupSharedService.getEntityById(scheduleSetupSharedGroupDTOPost.getSetupSharedId());
        Group group = groupService.getEntityById(scheduleSetupSharedGroupDTOPost.getGroupId());

        SharedGroup scheduleSetupSharedGroup = SharedGroup.builder()
                .setupShared(scheduleSetupShared)
                .group(group)
                .build();
        return scheduleSetupSharedGroup;
    }

    @Override
    public SharedGroup fromPatchToEntity(SharedGroupDTOPatch scheduleSetupSharedGroupDTOPatch, Long scheduleSetupSharedGroupId) {

        SharedGroup existingSharedGroup = scheduleSetupSharedGroupRepository.findById(scheduleSetupSharedGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Setup-Shared-Group not found with id: " + scheduleSetupSharedGroupId));

        if(scheduleSetupSharedGroupDTOPatch.getSetupSharedId() != null){
            SetupShared scheduleSetupShared = scheduleSetupSharedService.getEntityById(scheduleSetupSharedGroupDTOPatch.getSetupSharedId());
            existingSharedGroup.setSetupShared(scheduleSetupShared);
        }

        if(scheduleSetupSharedGroupDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(scheduleSetupSharedGroupDTOPatch.getGroupId());
            existingSharedGroup.setGroup(group);
        }

        return existingSharedGroup;

    }
}
