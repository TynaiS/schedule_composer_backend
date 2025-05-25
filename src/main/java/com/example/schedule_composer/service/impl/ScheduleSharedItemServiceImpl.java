package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.entity.ScheduleSharedItem;
import com.example.schedule_composer.mappers.ScheduleSharedItemMapper;
import com.example.schedule_composer.repository.ScheduleSharedItemRepository;
import com.example.schedule_composer.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleSharedItemServiceImpl implements ScheduleSharedItemService {

    private final ScheduleSharedItemRepository scheduleSharedItemRepository;
    private final ScheduleSharedItemMapper scheduleSharedItemMapper;
    private final ScheduleVersionService scheduleVersionService;
    private final SetupSharedItemService setupSharedItemService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;

    @Override
    public ScheduleSharedItemDTOGet getById(Long id) {
        return scheduleSharedItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleSharedItemDTOGet create(ScheduleSharedItemDTOPost createDto) {
        ScheduleSharedItem savedEntity = scheduleSharedItemRepository.save(scheduleSharedItemMapper.fromPostToEntity(createDto));
        return scheduleSharedItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedItemDTOGet create(ScheduleSharedItem createEntity) {
        ScheduleSharedItem savedEntity = scheduleSharedItemRepository.save(createEntity);
        return scheduleSharedItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleSharedItemDTOGet update(Long scheduleSharedItemId, ScheduleSharedItemDTOPatch updateDto) {
        ScheduleSharedItem existing = getEntityById(scheduleSharedItemId);
        ScheduleSharedItem updatedEntity = scheduleSharedItemRepository.save(scheduleSharedItemMapper.fromPatchToEntity(updateDto, existing));
        return scheduleSharedItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleSharedItemRepository.deleteById(id);
    }

    @Override
    public List<ScheduleSharedItemDTOGet> getAll() {
        List<ScheduleSharedItem> entities = scheduleSharedItemRepository.findAll();

        return scheduleSharedItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public ScheduleSharedItem getEntityById(Long id) {
        ScheduleSharedItem entity = scheduleSharedItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleSharedItem not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleSharedItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleSharedItem not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleSharedItem> getAllEntities() {
        return scheduleSharedItemRepository.findAll();
    }

    @Override
    public ScheduleSharedItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleSharedItemId) {
        return scheduleSharedItemMapper.fromEntityToGet(getEntityByIdForUserScheduleVersion(userId, scheduleSharedItemId));
    }

    @Override
    public List<ScheduleSharedItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        return scheduleSharedItemMapper.fromEntityListToGetList(getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId));
    }

    @Override
    public ScheduleSharedItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, ScheduleSharedItemDTOPost request) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        ScheduleSharedItem scheduleSharedItem = scheduleSharedItemMapper.fromPostToEntity(request);

        SetupSharedItem setupSharedItem = setupSharedItemService.getEntityByIdForUserScheduleVersion(userId, request.getSetupSharedItemId());
        Room room = roomService.getEntityByIdForUserSchedule(userId, request.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, request.getTimeSlotIds());

        scheduleSharedItem.setScheduleVersion(scheduleVersion);
        scheduleSharedItem.setSetupSharedItem(setupSharedItem);
        scheduleSharedItem.setRoom(room);
        scheduleSharedItem.setTimeSlots(timeSlots);

        return scheduleSharedItemMapper.fromEntityToGet(scheduleSharedItemRepository.save(scheduleSharedItem));

    }

    @Override
    public ScheduleSharedItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleSharedItemId, ScheduleSharedItemDTOPatch patchRequest) {
        ScheduleSharedItem scheduleSharedItem = getEntityByIdForUserScheduleVersion(userId, scheduleSharedItemId);

        scheduleSharedItem = scheduleSharedItemMapper.fromPatchToEntity(patchRequest, scheduleSharedItem);

        if(patchRequest.getSetupSharedItemId() != null){
            SetupSharedItem setupSharedItem = setupSharedItemService.getEntityByIdForUserScheduleVersion(userId, patchRequest.getSetupSharedItemId());
            scheduleSharedItem.setSetupSharedItem(setupSharedItem);
        }

        if(patchRequest.getRoomId() != null){
            Room room = roomService.getEntityByIdForUserSchedule(userId, patchRequest.getRoomId());
            scheduleSharedItem.setRoom(room);
        }

        if(patchRequest.getTimeSlotIds() != null){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, patchRequest.getTimeSlotIds());
            scheduleSharedItem.setTimeSlots(timeSlots);
        }

        return scheduleSharedItemMapper.fromEntityToGet(scheduleSharedItemRepository.save(scheduleSharedItem));
    }

    @Override
    public void deleteByIdForUserScheduleVersion(Long userId, Long scheduleSharedItemId) {
        ScheduleSharedItem scheduleSharedItem = getEntityByIdForUserScheduleVersion(userId, scheduleSharedItemId);
        scheduleSharedItemRepository.delete(scheduleSharedItem);
    }

    @Override
    public ScheduleSharedItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleSharedItemId) {
        ScheduleSharedItem scheduleSharedItem = getEntityById(scheduleSharedItemId);
        scheduleVersionService.checkUserAccessToScheduleVersion(scheduleSharedItem.getScheduleVersion(), userId);
        return scheduleSharedItem;
    }

    @Override
    public List<ScheduleSharedItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        return scheduleSharedItemRepository.findAllByScheduleVersionId(scheduleVersion.getId());
    }
}
