package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.TimeSlotDTOPatch;
import com.example.schedule_composer.dto.post.TimeSlotDTOPost;
import com.example.schedule_composer.entity.TimeSlot;

import java.util.List;

public interface TimeSlotService {

    TimeSlotDTOGet getById(Long id);
    TimeSlotDTOGet create(TimeSlotDTOPost createDto);
    TimeSlotDTOGet update(Long id, TimeSlotDTOPatch updateDto);
    void deleteById(Long id);
    List<TimeSlotDTOGet> getAll();


    TimeSlot getEntityById(Long id);
    Boolean checkIfExists(Long id);
    TimeSlot checkIfExistsAndGetEntity(Long id);
    List<TimeSlot> checkIfAllExistAndGetEntities(List<Long> timeSlotIds);
    List<TimeSlot> getAllEntities();


    TimeSlotDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long timeSlotId);
    List<TimeSlotDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    TimeSlotDTOGet createForUserSchedule(Long userId, Long scheduleId, TimeSlotDTOPost request);
    TimeSlotDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long timeSlotId, TimeSlotDTOPatch request);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long timeSlotId);


    TimeSlot getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long timeSlotId);
    List<TimeSlot> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
    List<TimeSlot> checkIfAllExistAndGetEntitiesForUserSchedule(Long userId, Long scheduleId, List<Long> timeSlotIds);
}
