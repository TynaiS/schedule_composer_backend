package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.entity.Schedule;
import com.example.schedule_composer.mappers.impl.RoomMapperImpl;
import com.example.schedule_composer.dto.patch.RoomDTOPatch;
import com.example.schedule_composer.dto.post.RoomDTOPost;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.repository.RoomRepository;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.ScheduleService;
import com.example.schedule_composer.utils.types.RoomType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapperImpl roomMapper;
    private final ScheduleService scheduleService;

    @Override
    public RoomDTOGet getById(Long id) {
        return roomMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public RoomDTOGet create(RoomDTOPost createDto) {
        Room savedEntity = roomRepository.save(roomMapper.fromPostToEntity(createDto));
        return roomMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public RoomDTOGet update(Long id, RoomDTOPatch updateDto) {
        Room existing = getEntityById(id);
        Room updatedEntity = roomRepository.save(roomMapper.fromPatchToEntity(updateDto, existing));
        return roomMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if (checkIfExists(id)) roomRepository.deleteById(id);
    }

    @Override
    public List<RoomDTOGet> getAll() {
        return roomMapper.fromEntityListToGetList(roomRepository.findAll());
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
    public Room getEntityById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new EntityNotFoundException("Room not found with id: " + id);
        }
        return true;
    }

    @Override
    public RoomDTOGet getByIdForUserSchedule(Long userId, Long roomId) {
        return roomMapper.fromEntityToGet(getEntityByIdForUserSchedule(userId, roomId));
    }

    @Override
    public List<RoomDTOGet> getAllForUserSchedule(Long userId, Long scheduleId) {
        return roomMapper.fromEntityListToGetList(getAllEntitiesForUserSchedule(userId, scheduleId));
    }

    @Override
    public RoomDTOGet createForUserSchedule(Long userId, Long scheduleId, RoomDTOPost request) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        Room room = roomMapper.fromPostToEntity(request);
        room.setSchedule(schedule);
        Room saved = roomRepository.save(room);
        return roomMapper.fromEntityToGet(saved);
    }

    @Override
    public RoomDTOGet updateForUserSchedule(Long userId, Long roomId, RoomDTOPatch patchRequest) {
        Room room = getEntityByIdForUserSchedule(userId, roomId);

        room = roomMapper.fromPatchToEntity(patchRequest, room);
        Room updated = roomRepository.save(room);
        return roomMapper.fromEntityToGet(updated);
    }

    @Override
    public void deleteByIdForUserSchedule(Long userId, Long roomId) {
        Room room = getEntityByIdForUserSchedule(userId, roomId);

        roomRepository.delete(room);
    }

    @Override
    public Room getEntityByIdForUserSchedule(Long userId, Long roomId) {
        Room room = getEntityById(roomId);
        scheduleService.checkUserAccessToSchedule(room.getSchedule(), userId);
        return room;
    }

    @Override
    public List<Room> getAllEntitiesForUserSchedule(Long userId, Long scheduleId) {
        Schedule schedule = scheduleService.getEntityByIdForUser(userId, scheduleId);

        return roomRepository.findAllByScheduleId(schedule.getId());
    }
}
