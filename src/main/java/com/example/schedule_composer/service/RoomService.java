package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.repository.RoomRepository;
import com.example.schedule_composer.utils.RoomType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDTOGet> getRooms() {
//        return roomRepository.findAll();
        return null;
//        to be implemented;
    }

    public RoomDTOGet getRoomById(Long id) {
//        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found with id " + id));
        return null;
//        to be implemented;
    }

    public List<Room> getLabs() {
        return roomRepository.findByType(RoomType.LAB);
    }

    public List<Room> getClassrooms() {
        return roomRepository.findByType(RoomType.CLASSROOM);
    }
}
