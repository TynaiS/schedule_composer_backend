package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.SetupItemDTOGet;
import com.example.schedule_composer.dto.patch.SetupItemDTOPatch;
import com.example.schedule_composer.dto.post.SetupItemDTOPost;
import com.example.schedule_composer.entity.SetupItem;


import java.util.List;

public interface SetupItemService {

    SetupItemDTOGet getById(Long id);
    SetupItemDTOGet create(SetupItemDTOPost createDto);
    SetupItemDTOGet update(Long id, SetupItemDTOPatch updateDto);
    void deleteById(Long id);
    List<SetupItemDTOGet> getAll();
    List<SetupItemDTOGet> getAllByGroupId(Long groupId);


    SetupItem getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<SetupItem> getAllEntities();



    SetupItemDTOGet getByIdForUser(Long userId, Long setupItemId);
    List<SetupItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId);
    List<SetupItemDTOGet> getAllByGroupIdForUserScheduleVersion(Long userId, Long scheduleVersionId, Long groupId);
    SetupItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, SetupItemDTOPost request);
    SetupItemDTOGet updateForUser(Long userId, Long setupItemId, SetupItemDTOPatch patchRequest);
    void deleteByIdForUser(Long userId, Long setupItemId);

    SetupItem getEntityByIdForUserScheduleVersion(Long userId, Long setupItemId);
    List<SetupItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId);
    List<SetupItem> getAllEntitiesByGroupIdForUserScheduleVersion(Long userId, Long scheduleVersionId, Long groupId);

}
