package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.patch.GroupDTOPatch;
import com.example.schedule_composer.dto.post.GroupDTOPost;
import com.example.schedule_composer.entity.Group;

import java.util.List;

public interface GroupService {

    GroupDTOGet getById(Long id);
    GroupDTOGet create(GroupDTOPost createDto);
    GroupDTOGet update(Long id, GroupDTOPatch updateDto);
    void deleteById(Long id);
    List<GroupDTOGet> getAll();


    Group getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<Group> getAllEntities();
    List<Group> checkIfAllExistAndGetEntities(List<Long> groupIds);


    GroupDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long groupId);
    List<GroupDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    GroupDTOGet createForUserSchedule(Long userId, Long scheduleId, GroupDTOPost request);
    GroupDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long groupId, GroupDTOPatch patchRequest);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long groupId);



    Group getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long groupId);
    List<Group> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
    List<Group> checkIfAllExistAndGetEntitiesForUserSchedule(Long userId, Long scheduleId, List<Long> groupIds);

}

