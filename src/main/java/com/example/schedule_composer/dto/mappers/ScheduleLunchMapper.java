package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
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

@Component
public class ScheduleLunchMapper implements DTOMapper<ScheduleLunchDTOGet, ScheduleLunchDTOPost, ScheduleLunchDTOPatch, ScheduleLunch, Long> {

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
                timeSlotMapper.fromEntityToGet(scheduleLunch.getStartTimeSlot()),
                timeSlotMapper.fromEntityToGet(scheduleLunch.getEndTimeSlot()));
        return scheduleLunchGet;
    }

    @Override
    public ScheduleLunch fromPostToEntity(ScheduleLunchDTOPost scheduleLunchDTOPost) {
        Group group = groupService.getEntityById(scheduleLunchDTOPost.getGroupId());
        TimeSlot startTimeSlot = timeSlotService.getEntityById(scheduleLunchDTOPost.getStartTimeSlotId());
        TimeSlot endTimeSlot = timeSlotService.getEntityById(scheduleLunchDTOPost.getEndTimeSlotId());


        ScheduleLunch scheduleLunch = ScheduleLunch.builder()
                .group(group)
                .day(scheduleLunchDTOPost.getDay())
                .startTimeSlot(startTimeSlot)
                .endTimeSlot(endTimeSlot)
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

        if(scheduleLunchDTOPatch.getStartTimeSlotId() != null){
            TimeSlot startTimeSlot = timeSlotService.getEntityById(scheduleLunchDTOPatch.getStartTimeSlotId());
            existingScheduleLunch.setStartTimeSlot(startTimeSlot);
        }

        if(scheduleLunchDTOPatch.getEndTimeSlotId() != null){
            TimeSlot endTimeSlot = timeSlotService.getEntityById(scheduleLunchDTOPatch.getEndTimeSlotId());
            existingScheduleLunch.setEndTimeSlot(endTimeSlot);
        }

        return existingScheduleLunch;

    }
}
