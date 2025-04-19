package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.ScheduleLunchDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleLunchDTOPost;
import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.ScheduleLunch;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.service.GroupService;
import com.example.schedule_composer.repository.ScheduleLunchRepository;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class
ScheduleLunchMapper implements DTOMapper<ScheduleLunchDTOGet, ScheduleLunchDTOPost, ScheduleLunchDTOPatch, ScheduleLunch, Long> {

    private final ScheduleLunchRepository scheduleLunchRepository;
    private final GroupService groupService;
    private final TimeSlotService timeSlotService;
    private final GroupMapper groupMapper;
    private final TimeSlotMapper timeSlotMapper;


    @Autowired
    public ScheduleLunchMapper(ScheduleLunchRepository scheduleLunchRepository, GroupService groupService, TimeSlotService timeSlotService, CourseMapper courseMapper, GroupMapper groupMapper, TimeSlotMapper timeSlotMapper) {
        this.scheduleLunchRepository = scheduleLunchRepository;
        this.groupService = groupService;
        this.timeSlotService = timeSlotService;
        this.groupMapper = groupMapper;
        this.timeSlotMapper = timeSlotMapper;
    }
    @Override
    public ScheduleLunchDTOGet fromEntityToGet(ScheduleLunch scheduleLunch) {
        ScheduleLunchDTOGet scheduleLunchGet = new ScheduleLunchDTOGet(
                scheduleLunch.getId(),
                groupMapper.fromEntityToGet(scheduleLunch.getGroup()),
                scheduleLunch.getDay(),
                timeSlotMapper.fromEntityListToGetList(scheduleLunch.getTimeSlots()));
        return scheduleLunchGet;
    }

    @Override
    public List<ScheduleLunchDTOGet> fromEntityListToGetList(List<ScheduleLunch> scheduleLunches) {
        return scheduleLunches.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleLunch fromPostToEntity(ScheduleLunchDTOPost scheduleLunchDTOPost) {
        Group group = groupService.getEntityById(scheduleLunchDTOPost.getGroupId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleLunchDTOPost.getTimeSlotIds());


        ScheduleLunch scheduleLunch = ScheduleLunch.builder()
                .group(group)
                .day(scheduleLunchDTOPost.getDay())
                .timeSlots(timeSlots)
                .build();
        return scheduleLunch;
    }

    @Override
    public ScheduleLunch fromPatchToEntity(ScheduleLunchDTOPatch scheduleLunchDTOPatch, Long scheduleLunchId) {

        ScheduleLunch existingScheduleLunch = scheduleLunchRepository.findById(scheduleLunchId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule lunch item not found with id: " + scheduleLunchId));

        if(scheduleLunchDTOPatch.getGroupId() != null){
            Group group = groupService.getEntityById(scheduleLunchDTOPatch.getGroupId());
            existingScheduleLunch.setGroup(group);
        }

        if(scheduleLunchDTOPatch.getDay() != null){
            existingScheduleLunch.setDay(scheduleLunchDTOPatch.getDay());
        }

        if(scheduleLunchDTOPatch.getTimeSlotIds() != null && scheduleLunchDTOPatch.getTimeSlotIds().size() > 0){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleLunchDTOPatch.getTimeSlotIds());
            existingScheduleLunch.setTimeSlots(timeSlots);
        }

        return existingScheduleLunch;

    }
}
