package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleLunchItemDTOGet;
import com.example.schedule_composer.mappers.DTOMapper;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.ScheduleLunchItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchItemDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.ScheduleLunchItem;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.repository.ScheduleLunchItemRepository;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class
ScheduleLunchItemMapper implements DTOMapper<ScheduleLunchItemDTOGet, ScheduleLunchItemDTOPost, ScheduleLunchItemDTOPatch, ScheduleLunchItem, Long> {

    private final ScheduleLunchItemRepository scheduleLunchItemRepository;
    private final GroupService groupService;
    private final TimeSlotService timeSlotService;
    private final GroupMapper groupMapper;
    private final TimeSlotMapper timeSlotMapper;

    @Override
    public ScheduleLunchItemDTOGet fromEntityToGet(ScheduleLunchItem scheduleLunchItem) {
        ScheduleLunchItemDTOGet scheduleLunchGet = new ScheduleLunchItemDTOGet(
                scheduleLunchItem.getId(),
                groupMapper.fromEntityToGet(scheduleLunchItem.getGroup()),
                scheduleLunchItem.getDay(),
                timeSlotMapper.fromEntityListToGetList(scheduleLunchItem.getTimeSlots()));
        return scheduleLunchGet;
    }

    @Override
    public List<ScheduleLunchItemDTOGet> fromEntityListToGetList(List<ScheduleLunchItem> scheduleLunchItems) {
        return scheduleLunchItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleLunchItem fromPostToEntity(ScheduleLunchItemDTOPost scheduleLunchItemDTOPost) {
        Group group = groupService.getEntityById(scheduleLunchItemDTOPost.getGroupId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleLunchItemDTOPost.getTimeSlotIds());


        ScheduleLunchItem scheduleLunchItem = ScheduleLunchItem.builder()
                .group(group)
                .day(scheduleLunchItemDTOPost.getDay())
                .timeSlots(timeSlots)
                .build();
        return scheduleLunchItem;
    }

    @Override
    public ScheduleLunchItem fromPatchToEntity(ScheduleLunchItemDTOPatch scheduleLunchItemDTOPatch, Long scheduleLunchId) {

        ScheduleLunchItem existingScheduleLunchItem = scheduleLunchItemRepository.findById(scheduleLunchId)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem lunch item not found with id: " + scheduleLunchId));

        if(scheduleLunchItemDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(scheduleLunchItemDTOPatch.getGroupId());
            existingScheduleLunchItem.setGroup(group);
        }

        if(scheduleLunchItemDTOPatch.getDay() != null){
            existingScheduleLunchItem.setDay(scheduleLunchItemDTOPatch.getDay());
        }

        if(scheduleLunchItemDTOPatch.getTimeSlotIds() != null && scheduleLunchItemDTOPatch.getTimeSlotIds().size() > 0){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleLunchItemDTOPatch.getTimeSlotIds());
            existingScheduleLunchItem.setTimeSlots(timeSlots);
        }

        return existingScheduleLunchItem;

    }
}
