package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.ScheduleSharedDTOGet;
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
public class ScheduleSharedMapper implements DTOMapper<ScheduleSharedDTOGet, ScheduleSharedDTOPost, ScheduleSharedDTOPatch, ScheduleShared, Long>{

    private final ScheduleSharedRepository scheduleSharedCourseRepository;
    private final SetupSharedService scheduleSetupSharedService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final SetupSharedMapper scheduleSetupSharedMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;



    @Autowired
    public ScheduleSharedMapper(ScheduleSharedRepository scheduleSharedCourseRepository, SetupSharedService scheduleSetupSharedService, RoomService roomService, TimeSlotService timeSlotService, CourseMapper courseMapper, SetupSharedMapper scheduleSetupSharedMapper, RoomMapper roomMapper, TimeSlotMapper timeSlotMapper) {
        this.scheduleSharedCourseRepository = scheduleSharedCourseRepository;
        this.scheduleSetupSharedService = scheduleSetupSharedService;
        this.roomService = roomService;
        this.timeSlotService = timeSlotService;
        this.scheduleSetupSharedMapper = scheduleSetupSharedMapper;
        this.roomMapper = roomMapper;
        this.timeSlotMapper = timeSlotMapper;
    }
    @Override
    public ScheduleSharedDTOGet fromEntityToGet(ScheduleShared schedule) {
        ScheduleSharedDTOGet scheduleGet = new ScheduleSharedDTOGet(
                schedule.getId(),
                scheduleSetupSharedMapper.fromEntityToGet(schedule.getSetupShared()),
                roomMapper.fromEntityToGet(schedule.getRoom()),
                schedule.getDay(),
                timeSlotMapper.fromEntityListToGetList(schedule.getTimeSlots()),
                schedule.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public List<ScheduleSharedDTOGet> fromEntityListToGetList(List<ScheduleShared> scheduleSharedCourses) {
        return scheduleSharedCourses.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleShared fromPostToEntity(ScheduleSharedDTOPost scheduleSharedCourseDTOPost) {
        SetupShared scheduleSetupShared = scheduleSetupSharedService.getEntityById(scheduleSharedCourseDTOPost.getSetupSharedId());
        Room room = roomService.getEntityById(scheduleSharedCourseDTOPost.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedCourseDTOPost.getTimeSlotIds());


        ScheduleShared schedule = ScheduleShared.builder()
                .setupShared(scheduleSetupShared)
                .room(room)
                .day(scheduleSharedCourseDTOPost.getDay())
                .timeSlots(timeSlots)
                .teachingMode(scheduleSharedCourseDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public ScheduleShared fromPatchToEntity(ScheduleSharedDTOPatch scheduleSharedCourseDTOPatch, Long scheduleId) {

        ScheduleShared existingScheduleShared = scheduleSharedCourseRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + scheduleId));

        if(scheduleSharedCourseDTOPatch.getSetupSharedId() != null){
            SetupShared scheduleSetupShared = scheduleSetupSharedService.getEntityById(scheduleSharedCourseDTOPatch.getSetupSharedId());
            existingScheduleShared.setSetupShared(scheduleSetupShared);
        }

        if(scheduleSharedCourseDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleSharedCourseDTOPatch.getRoomId());
            existingScheduleShared.setRoom(room);
        }

        if(scheduleSharedCourseDTOPatch.getDay() != null){
            existingScheduleShared.setDay(scheduleSharedCourseDTOPatch.getDay());
        }

        if(scheduleSharedCourseDTOPatch.getTimeSlotIds() != null && scheduleSharedCourseDTOPatch.getTimeSlotIds().size() > 0){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedCourseDTOPatch.getTimeSlotIds());
            existingScheduleShared.setTimeSlots(timeSlots);
        }


        if(scheduleSharedCourseDTOPatch.getTeachingMode() != null){
            existingScheduleShared.setTeachingMode(scheduleSharedCourseDTOPatch.getTeachingMode());
        }

        return existingScheduleShared;

    }
}
