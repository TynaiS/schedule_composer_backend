package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.mappers.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDTOGet fromEntityToGet(Room room) {
        RoomDTOGet roomGet = RoomDTOGet.builder()
                .id(room.getId())
                .scheduleId(room.getSchedule().getId())
                .roomNum(room.getRoomNum())
                .type(room.getType())
                .size(room.getSize())
                .build();
        return roomGet;
    }

    @Override
    public List<RoomDTOGet> fromEntityListToGetList(List<Room> rooms) {
        return rooms.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Room fromPostToEntity(RoomDTOPost roomDTOPost) {
        Room room = Room.builder()
                .roomNum(roomDTOPost.getRoomNum())
                .type(roomDTOPost.getType())
                .size(roomDTOPost.getSize())
                .build();
        return room;
    }

    @Override
    public Room fromPatchToEntity(RoomDTOPatch roomDTOPatch, Room roomToUpdate) {

        if (roomDTOPatch.getRoomNum() != null) {
            if (roomDTOPatch.getRoomNum().isBlank()) {
                throw new IllegalArgumentException("Room number cannot be blank");
            }
            roomToUpdate.setRoomNum(roomDTOPatch.getRoomNum());
        }

        if (roomDTOPatch.getSize() != null){
            roomToUpdate.setSize(roomDTOPatch.getSize());
        }

        return roomToUpdate;

    }
}
