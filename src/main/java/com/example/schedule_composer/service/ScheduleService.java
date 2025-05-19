package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.entity.User;

import java.util.List;

public interface ScheduleService {

    ScheduleDTOGet getById(Long id);
    ScheduleDTOGet create(ScheduleDTOPost createDto);
    ScheduleDTOGet create(Schedule createEntity);
    ScheduleDTOGet update(Long id, ScheduleDTOPatch updateDto);
    void deleteById(Long id);
    List<ScheduleDTOGet> getAll();


    Schedule getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<Schedule> getAllEntities();


    ScheduleDTOGet getByIdForUser(Long userId, Long scheduleId);
    List<ScheduleDTOGet> getAllForUserOwner(Long userId);
    List<ScheduleDTOGet> getAllForUserEditor(Long userId);

    ScheduleDTOGet createForUser(User user, ScheduleDTOPost request);
    ScheduleDTOGet updateForUser(Long userId, Long scheduleId, ScheduleDTOPatch patchRequest);
    void deleteByIdForUser(Long userId, Long scheduleId);
    void checkUserAccessToSchedule(Schedule schedule, Long userId);
    void checkScheduleId(Schedule schedule, Long scheduleId, String entityName);


    Schedule getEntityByIdForUser(Long userId, Long scheduleId);
    List<Schedule> getAllEntitiesForUserOwner(Long userId);
    List<Schedule> getAllEntitiesForUserEditor(Long userId);

}
