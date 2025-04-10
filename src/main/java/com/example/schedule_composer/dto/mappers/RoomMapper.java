package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper implements DTOMapper<RoomDTOGet, RoomDTOPost, RoomDTOPatch, Room, Long>{

    private final RoomRepository roomRepository;

    @Autowired
    public RoomMapper(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public RoomDTOGet fromEntityToGet(Room room) {
        RoomDTOGet roomGet = new RoomDTOGet(room.getId(), room.getRoomNum(), room.getType());
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
                .build();
        return room;
    }

    @Override
    public Room fromPatchToEntity(RoomDTOPatch roomDTOPatch, Long roomId) {

        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Setup not found with id: " + roomId));

        if (roomDTOPatch.getRoomNum() != null) {
            if (roomDTOPatch.getRoomNum().isBlank()) {
                throw new IllegalArgumentException("Room number cannot be blank");
            }
            existingRoom.setRoomNum(roomDTOPatch.getRoomNum());
        }

        return existingRoom;

    }
}
