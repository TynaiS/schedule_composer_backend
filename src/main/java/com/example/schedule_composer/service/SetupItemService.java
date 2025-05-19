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



    SetupItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupItemId);
    List<SetupItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId);
    List<SetupItemDTOGet> getAllByGroupIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long groupId);
    SetupItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, SetupItemDTOPost request);
    SetupItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupItemId, SetupItemDTOPatch patchRequest);
    void deleteByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupItemId);

    SetupItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long setupItemId);
    List<SetupItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId);
    List<SetupItem> getAllEntitiesByGroupIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long groupId);

}
