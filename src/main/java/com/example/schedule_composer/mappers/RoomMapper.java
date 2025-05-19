package com.example.schedule_composer.mappers;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;

import java.util.List;

public interface RoomMapper {
    RoomDTOGet fromEntityToGet(Room room);
    List<RoomDTOGet> fromEntityListToGetList(List<Room> rooms);
    Room fromPostToEntity(RoomDTOPost roomDTOPost);
    Room fromPatchToEntity(RoomDTOPatch roomDTOPatch, Room roomToUpdate);
}
