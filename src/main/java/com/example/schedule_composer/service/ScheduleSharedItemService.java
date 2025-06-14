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


    ScheduleSharedItemDTOGet getByIdForUser(Long userId, Long scheduleSharedItemId);
    List<ScheduleSharedItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId);
    ScheduleSharedItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, ScheduleSharedItemDTOPost request);
    ScheduleSharedItemDTOGet updateForUser(Long userId, Long scheduleSharedItemId, ScheduleSharedItemDTOPatch patchRequest);
    void deleteByIdForUser(Long userId, Long scheduleSharedItemId);
    void deleteAllForUserScheduleVersion(Long userId, Long scheduleVersionId);


    ScheduleSharedItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleSharedItemId);
    List<ScheduleSharedItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId);
}
