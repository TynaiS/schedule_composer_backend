package com.example.schedule_composer.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedItemDTOGet;
import com.example.schedule_composer.mappers.DTOMapper;
import com.example.schedule_composer.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.ScheduleSharedItemDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedItemDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleSharedItemRepository;
import com.example.schedule_composer.service.SetupSharedService;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleSharedItemMapper implements DTOMapper<ScheduleSharedItemDTOGet, ScheduleSharedItemDTOPost, ScheduleSharedItemDTOPatch, ScheduleSharedItem, Long> {

    private final ScheduleSharedItemRepository scheduleSharedItemRepository;
    private final SetupSharedService setupSharedService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final SetupSharedMapper setupSharedMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;


    @Override
    public ScheduleSharedItemDTOGet fromEntityToGet(ScheduleSharedItem schedule) {
        ScheduleSharedItemDTOGet scheduleGet = new ScheduleSharedItemDTOGet(
                schedule.getId(),
                setupSharedMapper.fromEntityToGet(schedule.getSetupShared()),
                roomMapper.fromEntityToGet(schedule.getRoom()),
                schedule.getDay(),
                timeSlotMapper.fromEntityListToGetList(schedule.getTimeSlots()),
                schedule.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public List<ScheduleSharedItemDTOGet> fromEntityListToGetList(List<ScheduleSharedItem> scheduleSharedItems) {
        return scheduleSharedItems.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleSharedItem fromPostToEntity(ScheduleSharedItemDTOPost scheduleSharedItemDTOPost) {
        SetupShared setupShared = setupSharedService.getEntityById(scheduleSharedItemDTOPost.getSetupSharedId());
        Room room = roomService.getEntityById(scheduleSharedItemDTOPost.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedItemDTOPost.getTimeSlotIds());


        ScheduleSharedItem schedule = ScheduleSharedItem.builder()
                .setupShared(setupShared)
                .room(room)
                .day(scheduleSharedItemDTOPost.getDay())
                .timeSlots(timeSlots)
                .teachingMode(scheduleSharedItemDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public ScheduleSharedItem fromPatchToEntity(ScheduleSharedItemDTOPatch scheduleSharedItemDTOPatch, Long scheduleId) {

        ScheduleSharedItem existingScheduleSharedItem = scheduleSharedItemRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("ScheduleItem-Shared-Course item not found with id: " + scheduleId));

        if(scheduleSharedItemDTOPatch.getSetupSharedId() != null){
            SetupShared setupShared = setupSharedService.getEntityById(scheduleSharedItemDTOPatch.getSetupSharedId());
            existingScheduleSharedItem.setSetupShared(setupShared);
        }

        if(scheduleSharedItemDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleSharedItemDTOPatch.getRoomId());
            existingScheduleSharedItem.setRoom(room);
        }

        if(scheduleSharedItemDTOPatch.getDay() != null){
            existingScheduleSharedItem.setDay(scheduleSharedItemDTOPatch.getDay());
        }

        if(scheduleSharedItemDTOPatch.getTimeSlotIds() != null && scheduleSharedItemDTOPatch.getTimeSlotIds().size() > 0){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedItemDTOPatch.getTimeSlotIds());
            existingScheduleSharedItem.setTimeSlots(timeSlots);
        }


        if(scheduleSharedItemDTOPatch.getTeachingMode() != null){
            existingScheduleSharedItem.setTeachingMode(scheduleSharedItemDTOPatch.getTeachingMode());
        }

        return existingScheduleSharedItem;

    }
}
