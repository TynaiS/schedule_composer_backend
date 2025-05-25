package com.example.schedule_composer.service.impl;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.entity.ScheduleLunchItem;
import com.example.schedule_composer.mappers.ScheduleLunchItemMapper;
import com.example.schedule_composer.repository.ScheduleLunchItemRepository;
import com.example.schedule_composer.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleLunchItemServiceImpl implements ScheduleLunchItemService {

    private final ScheduleLunchItemRepository scheduleLunchItemRepository;
    private final ScheduleLunchItemMapper scheduleLunchItemMapper;
    private final ScheduleVersionService scheduleVersionService;
    private final GroupService groupService;
    private final TimeSlotService timeSlotService;


    @Override
    public ScheduleLunchItemDTOGet getById(Long id) {
        return scheduleLunchItemMapper.fromEntityToGet(getEntityById(id));
    }

    @Override
    public ScheduleLunchItemDTOGet create(ScheduleLunchItemDTOPost createDto) {
        ScheduleLunchItem savedEntity = scheduleLunchItemRepository.save(scheduleLunchItemMapper.fromPostToEntity(createDto));
        return scheduleLunchItemMapper.fromEntityToGet(savedEntity);
    }

    @Override
    public ScheduleLunchItemDTOGet update(Long scheduleLunchItemId, ScheduleLunchItemDTOPatch updateDto) {
        ScheduleLunchItem existing = getEntityById(scheduleLunchItemId);
        ScheduleLunchItem updatedEntity = scheduleLunchItemRepository.save(scheduleLunchItemMapper.fromPatchToEntity(updateDto, existing));
        return scheduleLunchItemMapper.fromEntityToGet(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(checkIfExists(id)) scheduleLunchItemRepository.deleteById(id);
    }

    @Override
    public List<ScheduleLunchItemDTOGet> getAll() {
        List<ScheduleLunchItem> entities = scheduleLunchItemRepository.findAll();

        return scheduleLunchItemMapper.fromEntityListToGetList(entities);
    }

    @Override
    public ScheduleLunchItem getEntityById(Long id) {
        ScheduleLunchItem entity = scheduleLunchItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleLunchItem lunch item not found with id: " + id));
        return entity;
    }

    @Override
    public Boolean checkIfExists(Long id) {
        if (!scheduleLunchItemRepository.existsById(id)) {
            throw new EntityNotFoundException("ScheduleLunchItem lunch item not found with id: " + id);
        }
        return true;
    }

    @Override
    public List<ScheduleLunchItem> getAllEntities() {
        return scheduleLunchItemRepository.findAll();
    }

    @Override
    public ScheduleLunchItemDTOGet getByIdForUserScheduleVersion(Long userId, Long scheduleLunchItemId) {
        return scheduleLunchItemMapper.fromEntityToGet(getEntityByIdForUserScheduleVersion(userId, scheduleLunchItemId));

    }

    @Override
    public List<ScheduleLunchItemDTOGet> getAllForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        return scheduleLunchItemMapper.fromEntityListToGetList(getAllEntitiesForUserScheduleVersion(userId, scheduleVersionId));
    }

    @Override
    public ScheduleLunchItemDTOGet createForUserScheduleVersion(Long userId, Long scheduleVersionId, ScheduleLunchItemDTOPost request) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        ScheduleLunchItem scheduleLunchItem = scheduleLunchItemMapper.fromPostToEntity(request);

        Group group = groupService.getEntityByIdForUserSchedule(userId, request.getGroupId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, request.getTimeSlotIds());

        scheduleLunchItem.setScheduleVersion(scheduleVersion);
        scheduleLunchItem.setGroup(group);
        scheduleLunchItem.setTimeSlots(timeSlots);

        return scheduleLunchItemMapper.fromEntityToGet(scheduleLunchItemRepository.save(scheduleLunchItem));
    }

    @Override
    public ScheduleLunchItemDTOGet updateForUserScheduleVersion(Long userId, Long scheduleLunchItemId, ScheduleLunchItemDTOPatch patchRequest) {
        ScheduleLunchItem scheduleLunchItem = getEntityByIdForUserScheduleVersion(userId, scheduleLunchItemId);

        scheduleLunchItem = scheduleLunchItemMapper.fromPatchToEntity(patchRequest, scheduleLunchItem);


        if(patchRequest.getGroupId() != null){
            Group group = groupService.getEntityByIdForUserSchedule(userId, patchRequest.getGroupId());
            scheduleLunchItem.setGroup(group);
        }

        if(patchRequest.getTimeSlotIds() != null){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntitiesForUserSchedule(userId, patchRequest.getTimeSlotIds());
            scheduleLunchItem.setTimeSlots(timeSlots);
        }

        return scheduleLunchItemMapper.fromEntityToGet(scheduleLunchItemRepository.save(scheduleLunchItem));
    }

    @Override
    public void deleteByIdForUserScheduleVersion(Long userId, Long scheduleLunchItemId) {
        ScheduleLunchItem scheduleLunchItem = getEntityByIdForUserScheduleVersion(userId, scheduleLunchItemId);
        scheduleLunchItemRepository.delete(scheduleLunchItem);
    }

    @Override
    public ScheduleLunchItem getEntityByIdForUserScheduleVersion(Long userId, Long scheduleLunchItemId) {
        ScheduleLunchItem scheduleLunchItem = getEntityById(scheduleLunchItemId);
        scheduleVersionService.checkUserAccessToScheduleVersion(scheduleLunchItem.getScheduleVersion(), userId);
        return scheduleLunchItem;
    }

    @Override
    public List<ScheduleLunchItem> getAllEntitiesForUserScheduleVersion(Long userId, Long scheduleVersionId) {
        ScheduleVersion scheduleVersion = scheduleVersionService.getEntityByIdForUser(userId, scheduleVersionId);

        return scheduleLunchItemRepository.findAllByScheduleVersionId(scheduleVersion.getId());
    }


}
