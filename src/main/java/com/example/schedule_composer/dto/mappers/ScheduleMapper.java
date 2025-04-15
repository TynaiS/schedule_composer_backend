package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleRepository;
import com.example.schedule_composer.service.SetupService;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper implements DTOMapper<ScheduleDTOGet, ScheduleDTOPost, ScheduleDTOPatch, Schedule, Long>{

    private final ScheduleRepository scheduleRepository;
    private final SetupService setupService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final SetupMapper setupMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;



    @Autowired
    public ScheduleMapper(ScheduleRepository scheduleRepository, SetupService setupService, RoomService roomService, TimeSlotService timeSlotService, CourseMapper courseMapper, SetupMapper setupMapper, RoomMapper roomMapper, TimeSlotMapper timeSlotMapper) {
        this.scheduleRepository = scheduleRepository;
        this.setupService = setupService;
        this.roomService = roomService;
        this.timeSlotService = timeSlotService;
        this.setupMapper = setupMapper;
        this.roomMapper = roomMapper;
        this.timeSlotMapper = timeSlotMapper;
    }
    @Override
    public ScheduleDTOGet fromEntityToGet(Schedule schedule) {
        ScheduleDTOGet scheduleGet = new ScheduleDTOGet(
                schedule.getId(),
                setupMapper.fromEntityToGet(schedule.getSetup()),
                roomMapper.fromEntityToGet(schedule.getRoom()),
                schedule.getDay(),
                timeSlotMapper.fromEntityListToGetList(schedule.getTimeSlots()),
                schedule.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public List<ScheduleDTOGet> fromEntityListToGetList(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public Schedule fromPostToEntity(ScheduleDTOPost scheduleDTOPost) {
        Setup setup = setupService.getEntityById(scheduleDTOPost.getSetupId());
        Room room = roomService.getEntityById(scheduleDTOPost.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleDTOPost.getTimeSlotIds());


        Schedule schedule = Schedule.builder()
                .setup(setup)
                .room(room)
                .day(scheduleDTOPost.getDay())
                .timeSlots(timeSlots)
                .teachingMode(scheduleDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public Schedule fromPatchToEntity(ScheduleDTOPatch scheduleDTOPatch, Long scheduleId) {

        Schedule existingSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule item not found with id: " + scheduleId));


        if(scheduleDTOPatch.getSetupId() != null){
            Setup setup = setupService.getEntityById(scheduleDTOPatch.getSetupId());
            existingSchedule.setSetup(setup);
        }

        if(scheduleDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleDTOPatch.getRoomId());
            existingSchedule.setRoom(room);
        }

        if(scheduleDTOPatch.getDay() != null){
            existingSchedule.setDay(scheduleDTOPatch.getDay());
        }

        if(scheduleDTOPatch.getTimeSlotIds() != null){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleDTOPatch.getTimeSlotIds());
            existingSchedule.setTimeSlots(timeSlots);
        }

        if(scheduleDTOPatch.getTeachingMode() != null){
            existingSchedule.setTeachingMode(scheduleDTOPatch.getTeachingMode());
        }

        return existingSchedule;

    }
}
