package com.example.schedule_composer.dto.mappers.impl;

import com.example.schedule_composer.dto.get.ScheduleSharedDTOGet;
import com.example.schedule_composer.dto.mappers.DTOMapper;
import com.example.schedule_composer.dto.mappers.TimeSlotMapper;
import com.example.schedule_composer.dto.patch.ScheduleSharedDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleSharedRepository;
import com.example.schedule_composer.service.SetupSharedService;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleSharedMapper implements DTOMapper<ScheduleSharedDTOGet, ScheduleSharedDTOPost, ScheduleSharedDTOPatch, ScheduleShared, Long> {

    private final ScheduleSharedRepository scheduleSharedRepository;
    private final SetupSharedService setupSharedService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final SetupSharedMapper setupSharedMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;



    @Autowired
    public ScheduleSharedMapper(ScheduleSharedRepository scheduleSharedRepository, SetupSharedService setupSharedService, RoomService roomService, TimeSlotService timeSlotService, CourseMapper courseMapper, SetupSharedMapper setupSharedMapper, RoomMapper roomMapper, TimeSlotMapper timeSlotMapper) {
        this.scheduleSharedRepository = scheduleSharedRepository;
        this.setupSharedService = setupSharedService;
        this.roomService = roomService;
        this.timeSlotService = timeSlotService;
        this.setupSharedMapper = setupSharedMapper;
        this.roomMapper = roomMapper;
        this.timeSlotMapper = timeSlotMapper;
    }
    @Override
    public ScheduleSharedDTOGet fromEntityToGet(ScheduleShared schedule) {
        ScheduleSharedDTOGet scheduleGet = new ScheduleSharedDTOGet(
                schedule.getId(),
                setupSharedMapper.fromEntityToGet(schedule.getSetupShared()),
                roomMapper.fromEntityToGet(schedule.getRoom()),
                schedule.getDay(),
                timeSlotMapper.fromEntityListToGetList(schedule.getTimeSlots()),
                schedule.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public List<ScheduleSharedDTOGet> fromEntityListToGetList(List<ScheduleShared> scheduleShareds) {
        return scheduleShareds.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleShared fromPostToEntity(ScheduleSharedDTOPost scheduleSharedDTOPost) {
        SetupShared setupShared = setupSharedService.getEntityById(scheduleSharedDTOPost.getSetupSharedId());
        Room room = roomService.getEntityById(scheduleSharedDTOPost.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedDTOPost.getTimeSlotIds());


        ScheduleShared schedule = ScheduleShared.builder()
                .setupShared(setupShared)
                .room(room)
                .day(scheduleSharedDTOPost.getDay())
                .timeSlots(timeSlots)
                .teachingMode(scheduleSharedDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public ScheduleShared fromPatchToEntity(ScheduleSharedDTOPatch scheduleSharedDTOPatch, Long scheduleId) {

        ScheduleShared existingScheduleShared = scheduleSharedRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + scheduleId));

        if(scheduleSharedDTOPatch.getSetupSharedId() != null){
            SetupShared setupShared = setupSharedService.getEntityById(scheduleSharedDTOPatch.getSetupSharedId());
            existingScheduleShared.setSetupShared(setupShared);
        }

        if(scheduleSharedDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleSharedDTOPatch.getRoomId());
            existingScheduleShared.setRoom(room);
        }

        if(scheduleSharedDTOPatch.getDay() != null){
            existingScheduleShared.setDay(scheduleSharedDTOPatch.getDay());
        }

        if(scheduleSharedDTOPatch.getTimeSlotIds() != null && scheduleSharedDTOPatch.getTimeSlotIds().size() > 0){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedDTOPatch.getTimeSlotIds());
            existingScheduleShared.setTimeSlots(timeSlots);
        }


        if(scheduleSharedDTOPatch.getTeachingMode() != null){
            existingScheduleShared.setTeachingMode(scheduleSharedDTOPatch.getTeachingMode());
        }

        return existingScheduleShared;

    }
}
