package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.mappers.impl.RoomMapper;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.repository.RoomRepository;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository,RoomMapper roomMapper){
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public RoomDTOGet getById(Long id) {
        return roomMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public Room getEntityById(Long id) {
        Room entity = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new EntityNotFoundException("Room not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<RoomDTOGet> getAll() {
        List<Room> entities = roomRepository.findAll();

        return roomMapper.fromEntityListToGetList(entities);
    }

    @Override
    public List<Room> getAllEntities() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllLabEntities() {
        return roomRepository.findByType(RoomType.LAB);
    }

    @Override
    public List<Room> getAllClassroomEntities() {
        return roomRepository.findByType(RoomType.CLASSROOM);
    }

    @Override
    public RoomDTOGet create(RoomDTOPost createDto) {
        Room savedEntity = roomRepository.save(roomMapper.fromPostToEntity(createDto));
        return roomMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public RoomDTOGet update(Long id, RoomDTOPatch updateDto) {
        Room updatedEntity = roomRepository.save(roomMapper.fromPatchToEntity(updateDto, id));
        return roomMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) roomRepository.deleteById(id);
    }
}
