package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleItemDTOGet;
import com.example.schedule_composer.mappers.DTOMapper;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.ScheduleItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleItemRepository;
import com.example.schedule_composer.service.SetupService;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleItemMapper implements DTOMapper<ScheduleItemDTOGet, ScheduleItemDTOPost, ScheduleItemDTOPatch, ScheduleItem, Long> {

    private final ScheduleItemRepository scheduleItemRepository;
    private final SetupService setupService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final SetupMapper setupMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;


    @Override
    public ScheduleItemDTOGet fromEntityToGet(ScheduleItem scheduleItem) {
        ScheduleItemDTOGet scheduleGet = new ScheduleItemDTOGet(
                scheduleItem.getId(),
                setupMapper.fromEntityToGet(scheduleItem.getSetup()),
                roomMapper.fromEntityToGet(scheduleItem.getRoom()),
                scheduleItem.getDay(),
                timeSlotMapper.fromEntityListToGetList(scheduleItem.getTimeSlots()),
                scheduleItem.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public List<ScheduleItemDTOGet> fromEntityListToGetList(List<ScheduleItem> scheduleItems) {
        return scheduleItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleItem fromPostToEntity(ScheduleItemDTOPost scheduleItemDTOPost) {
        Setup setup = setupService.getEntityById(scheduleItemDTOPost.getSetupId());
        Room room = roomService.getEntityById(scheduleItemDTOPost.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleItemDTOPost.getTimeSlotIds());


        ScheduleItem scheduleItem = ScheduleItem.builder()
                .setup(setup)
                .room(room)
                .day(scheduleItemDTOPost.getDay())
                .timeSlots(timeSlots)
                .teachingMode(scheduleItemDTOPost.getTeachingMode())
                .build();
        return scheduleItem;
    }

    @Override
    public ScheduleItem fromPatchToEntity(ScheduleItemDTOPatch scheduleItemDTOPatch, Long scheduleId) {

        ScheduleItem existingScheduleItem = scheduleItemRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem item not found with id: " + scheduleId));


        if(scheduleItemDTOPatch.getSetupId() != null){
            Setup setup = setupService.getEntityById(scheduleItemDTOPatch.getSetupId());
            existingScheduleItem.setSetup(setup);
        }

        if(scheduleItemDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleItemDTOPatch.getRoomId());
            existingScheduleItem.setRoom(room);
        }

        if(scheduleItemDTOPatch.getDay() != null){
            existingScheduleItem.setDay(scheduleItemDTOPatch.getDay());
        }

        if(scheduleItemDTOPatch.getTimeSlotIds() != null){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleItemDTOPatch.getTimeSlotIds());
            existingScheduleItem.setTimeSlots(timeSlots);
        }

        if(scheduleItemDTOPatch.getTeachingMode() != null){
            existingScheduleItem.setTeachingMode(scheduleItemDTOPatch.getTeachingMode());
        }

        return existingScheduleItem;

    }
}
