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



    ScheduleVersionDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId);
    List<ScheduleVersionDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    ScheduleVersionDTOGet createForUserSchedule(Long userId, Long scheduleId, ScheduleVersionDTOPost request);
    ScheduleVersionDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId, ScheduleVersionDTOPatch patchRequest);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId);
    void checkScheduleVersionId(ScheduleVersion scheduleVersion, Long scheduleVersionId, String entityName);

    ScheduleVersion getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long scheduleVersionId);
    List<ScheduleVersion> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
}
