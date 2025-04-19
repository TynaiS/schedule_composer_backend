package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;

import java.util.List;

public interface RoomService {

    RoomDTOGet getById(Long id);

    Room getEntityById(Long id);

    Boolean checkIfExists(Long id);

    List<RoomDTOGet> getAll();

    List<Room> getAllEntities();

    List<Room> getAllLabEntities();

    List<Room> getAllClassroomEntities();

    RoomDTOGet create(RoomDTOPost createDto);

    RoomDTOGet update(Long id, RoomDTOPatch updateDto);

    void deleteById(Long id);
}
