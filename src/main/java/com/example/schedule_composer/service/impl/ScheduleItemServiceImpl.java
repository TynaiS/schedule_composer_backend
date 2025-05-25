package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.mappers.ScheduleItemMapper;
import com.example.schedule_composer.repository.ScheduleItemRepository;
import com.example.schedule_composer.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleItemServiceImpl implements ScheduleItemService {

    private final ScheduleItemRepository scheduleItemRepository;
    private final ScheduleItemMapper scheduleItemMapper;
    private final ScheduleVersionService scheduleVersionService;
    private final SetupItemService setupItemService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;

    @Override
    public ScheduleItemDTOGet getById(Long id) {
        return scheduleItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleItemDTOGet create(ScheduleItemDTOPost createDto) {
        ScheduleItem savedEntity = scheduleItemRepository.save(scheduleItemMapper.fromPostToEntity(createDto));
        return scheduleItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleItemDTOGet create(ScheduleItem createEntity) {
        ScheduleItem savedEntity = scheduleItemRepository.save(createEntity);
        return scheduleItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleItemDTOGet update(Long scheduleItemId, ScheduleItemDTOPatch updateDto) {
        ScheduleItem existing = getEntityById(scheduleItemId);
        ScheduleItem updatedEntity = scheduleItemRepository.save(scheduleItemMapper.fromPatchToEntity(updateDto, existing));
        return scheduleItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleItemRepository.deleteById(id);
    }

    @Override
    public ScheduleItem getEntityById(Long id) {
        ScheduleItem entity = scheduleItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem not found with id: " + id));
        return entity;
    }

    @Override
    public List<ScheduleItemDTOGet> getAll() {
        List<ScheduleItem> entities = scheduleItemRepository.findAll();

        return scheduleItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleItem not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleItem> getAllEntities() {
        return scheduleItemRepository.findAll();
    }

    @Override
    public ScheduleItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleItemId) {
        return scheduleItemMapper.fromEntityToGet(getEntityByIdForUserScheduleVersion(userId, scheduleItemId));
    }

    @Override
    public List<ScheduleItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        return scheduleItemMapper.fromEntityListToGetList(getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId));
    }

    @Override
    public ScheduleItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, ScheduleItemDTOPost request) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        ScheduleItem scheduleItem = scheduleItemMapper.fromPostToEntity(request);

        SetupItem setupItem = setupItemService.getEntityByIdForUserScheduleVersion(userId, request.getSetupItemId());
        Room room = roomService.getEntityByIdForUserSchedule(userId, request.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, request.getTimeSlotIds());

        scheduleItem.setScheduleVersion(scheduleVersion);
        scheduleItem.setSetupItem(setupItem);
        scheduleItem.setRoom(room);
        scheduleItem.setTimeSlots(timeSlots);

        return scheduleItemMapper.fromEntityToGet(scheduleItemRepository.save(scheduleItem));
    }

    @Override
    public ScheduleItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleItemId, ScheduleItemDTOPatch patchRequest) {
        ScheduleItem scheduleItem = getEntityByIdForUserScheduleVersion(userId, scheduleItemId);

        scheduleItem = scheduleItemMapper.fromPatchToEntity(patchRequest, scheduleItem);

        if(patchRequest.getSetupItemId() != null){
            SetupItem setupItem = setupItemService.getEntityByIdForUserScheduleVersion(userId, patchRequest.getSetupItemId());
            scheduleItem.setSetupItem(setupItem);
        }

        if(patchRequest.getRoomId() != null){
            Room room = roomService.getEntityByIdForUserSchedule(userId, patchRequest.getRoomId());
            scheduleItem.setRoom(room);
        }

        if(patchRequest.getTimeSlotIds() != null){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, patchRequest.getTimeSlotIds());
            scheduleItem.setTimeSlots(timeSlots);
        }

        return scheduleItemMapper.fromEntityToGet(scheduleItemRepository.save(scheduleItem));
    }

    @Override
    public void deleteByIdForUserScheduleVersion(Long userId, Long scheduleItemId) {
        ScheduleItem scheduleItem = getEntityByIdForUserScheduleVersion(userId, scheduleItemId);
        scheduleItemRepository.delete(scheduleItem);
    }

    @Override
    public ScheduleItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleItemId) {
        ScheduleItem scheduleItem = getEntityById(scheduleItemId);
        scheduleVersionService.checkUserAccessToScheduleVersion(scheduleItem.getScheduleVersion(), userId);
        return scheduleItem;
    }

    @Override
    public List<ScheduleItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        return scheduleItemRepository.findAllByScheduleVersionId(scheduleVersion.getId());
    }
}
