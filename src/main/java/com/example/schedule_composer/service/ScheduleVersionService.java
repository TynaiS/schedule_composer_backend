package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleVersionDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleVersionDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleVersionDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.ScheduleVersion;

import java.util.List;

public interface ScheduleVersionService {

    ScheduleVersionDTOGet getById(Long id);
    ScheduleVersionDTOGet create(ScheduleVersionDTOPost createDto);
    ScheduleVersionDTOGet create(ScheduleVersion createEntity);
    ScheduleVersionDTOGet update(Long id, ScheduleVersionDTOPatch updateDto);
    void deleteById(Long id);
    List<ScheduleVersionDTOGet> getAll();


    ScheduleVersion getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<ScheduleVersion> getAllEntities();



    ScheduleVersionDTOGet getByIdForUser(Long userId, Long scheduleVersionId);
    List<ScheduleVersionDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    ScheduleVersionDTOGet createForUserSchedule(Long userId, Long scheduleId, ScheduleVersionDTOPost request);
    ScheduleVersionDTOGet updateForUser(Long userId, Long scheduleVersionId, ScheduleVersionDTOPatch patchRequest);
    void deleteByIdForUser(Long userId, Long scheduleVersionId);
    void checkScheduleVersionId(ScheduleVersion scheduleVersion, Long scheduleVersionId, String entityName);

    ScheduleVersion getEntityByIdForUser(Long userId, Long scheduleVersionId);
    List<ScheduleVersion> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);

    void checkUserAccessToScheduleVersion(ScheduleVersion scheduleVersion, Long userId);
}
