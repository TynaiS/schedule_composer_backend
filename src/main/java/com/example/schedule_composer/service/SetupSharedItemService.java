package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupSharedItemDTOPost;
import com.example.schedule_composer.entity.SetupSharedItem;

import java.util.List;

public interface SetupSharedItemService {

    SetupSharedItemDTOGet getById(Long id);
    SetupSharedItemDTOGet create(SetupSharedItemDTOPost createDto);
    SetupSharedItemDTOGet update(Long id, SetupSharedItemDTOPatch updateDto);
    void deleteById(Long id);
    List<SetupSharedItemDTOGet> getAll();
    List<SetupSharedItemDTOGet> getAllByGroupId(Long groupId);


    SetupSharedItem getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<SetupSharedItem> getAllEntities();


    SetupSharedItemDTOGet getByIdForUser(Long userId, Long setupSharedItemId);
    SetupSharedItemDTOGet getByIdForUserSetupSharedSet(Long userId, Long setupSharedItemId);
    List<SetupSharedItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId);
    List<SetupSharedItemDTOGet> getAllForUserSetupSharedSet(Long userId, Long setupSharedSetId);
    List<SetupSharedItemDTOGet> getAllByGroupIdForUserSetupSharedSet(Long userId, Long setupSharedSetId, Long groupId);
    SetupSharedItemDTOGet createForUserSetupSharedSet(Long userId, Long setupSharedSetId, SetupSharedItemDTOPost request);
    SetupSharedItemDTOGet updateForUserSetupSharedSet(Long userId, Long setupSharedItemId, SetupSharedItemDTOPatch patchRequest);
    void deleteByIdForUserSetupSharedSet(Long userId, Long setupSharedItemId);


    SetupSharedItem getEntityByIdForUser(Long userId, Long scheduleVersionId);
    SetupSharedItem getEntityByIdForUserSetupSharedSet(Long userId, Long setupSharedItemId);
    List<SetupSharedItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId);
    List<SetupSharedItem> getAllEntitiesForUserSetupSharedSet(Long userId, Long setupSharedSetId);
    List<SetupSharedItem> getAllEntitiesByGroupIdForUserSetupSharedSet(Long userId, Long setupSharedSetId, Long groupId);


    //  ----  Shared groups ----


//    GroupDTOGet getGroupById(Long setupSharedId, Long groupId);
//
//    List<GroupDTOGet> getAllGroups(Long setupSharedId);
//
//    GroupDTOGet addGroup(Long setupSharedId, Long groupId);
//
//    void deleteGroupById(Long setupSharedId, Long groupId);
}
