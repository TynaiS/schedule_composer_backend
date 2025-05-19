package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.ScheduleSharedItem;

import java.util.List;

public interface ScheduleSharedItemService {

    ScheduleSharedItemDTOGet getById(Long id);
    ScheduleSharedItemDTOGet create(ScheduleSharedItemDTOPost createDto);
    ScheduleSharedItemDTOGet create(ScheduleSharedItem createEntity);
    ScheduleSharedItemDTOGet update(Long id, ScheduleSharedItemDTOPatch updateDto);
    void deleteById(Long id);
    List<ScheduleSharedItemDTOGet> getAll();



    ScheduleSharedItem getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<ScheduleSharedItem> getAllEntities();


    ScheduleSharedItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long scheduleSharedItemId);
    List<ScheduleSharedItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId);
    ScheduleSharedItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, ScheduleSharedItemDTOPost request);
    ScheduleSharedItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long scheduleSharedItemId, ScheduleSharedItemDTOPatch patchRequest);
    void deleteByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long scheduleSharedItemId);

    ScheduleSharedItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId, Long scheduleSharedItemId);
    List<ScheduleSharedItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleId, Long scheduleVersionId);
}
