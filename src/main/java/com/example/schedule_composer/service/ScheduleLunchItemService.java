package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.ScheduleLunchItem;

import java.util.List;

public interface ScheduleLunchItemService {

    ScheduleLunchItemDTOGet getById(Long id);
    ScheduleLunchItemDTOGet create(ScheduleLunchItemDTOPost createDto);
    ScheduleLunchItemDTOGet update(Long id, ScheduleLunchItemDTOPatch updateDto);
    void deleteById(Long id);
    List<ScheduleLunchItemDTOGet> getAll();


    ScheduleLunchItem getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<ScheduleLunchItem> getAllEntities();



    ScheduleLunchItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleLunchItemId);
    List<ScheduleLunchItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId);
    ScheduleLunchItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, ScheduleLunchItemDTOPost request);
    ScheduleLunchItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleLunchItemId, ScheduleLunchItemDTOPatch patchRequest);
    void deleteByIdForUserScheduleVersion(Long userId, Long scheduleLunchItemId);

    ScheduleLunchItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleLunchItemId);
    List<ScheduleLunchItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId);
}
