package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;

import java.util.List;

public interface RoomService {

    RoomDTOGet getById(Long id);
    RoomDTOGet create(RoomDTOPost createDto);
    RoomDTOGet update(Long id, RoomDTOPatch updateDto);
    void deleteById(Long id);
    List<RoomDTOGet> getAll();


    Room getEntityById(Long id);
    Boolean checkIfExists(Long id);
    List<Room> getAllEntities();
    List<Room> getAllLabEntities();
    List<Room> getAllClassroomEntities();


    RoomDTOGet getByIdForUserSchedule(Long userId, Long scheduleId, Long roomId);
    List<RoomDTOGet> getAllForUserSchedule(Long userId, Long scheduleId);
    RoomDTOGet createForUserSchedule(Long userId, Long scheduleId, RoomDTOPost request);
    RoomDTOGet updateForUserSchedule(Long userId, Long scheduleId, Long roomId, RoomDTOPatch patchRequest);
    void deleteByIdForUserSchedule(Long userId, Long scheduleId, Long roomId);


    Room getEntityByIdForUserSchedule(Long userId, Long scheduleId, Long roomId);
    List<Room> getAllEntitiesForUserSchedule(Long userId, Long scheduleId);
}
