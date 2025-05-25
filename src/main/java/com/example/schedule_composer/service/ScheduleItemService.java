package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.ScheduleItem;

import java.util.List;

public interface ScheduleItemService {

    ScheduleItemDTOGet getById(Long id);
    ScheduleItemDTOGet create(ScheduleItemDTOPost createDto);
    ScheduleItemDTOGet create(ScheduleItem createEntity);
    ScheduleItemDTOGet update(Long id, ScheduleItemDTOPatch updateDto);
    void deleteById(Long id);
    List<ScheduleItemDTOGet> getAll();


    ScheduleItem getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<ScheduleItem> getAllEntities();



    ScheduleItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleItemId);
    List<ScheduleItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId);
    ScheduleItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, ScheduleItemDTOPost request);
    ScheduleItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleItemId, ScheduleItemDTOPatch patchRequest);
    void deleteByIdForUserScheduleVersion(Long userId, Long scheduleItemId);

    ScheduleItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleItemId);
    List<ScheduleItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId);
}
