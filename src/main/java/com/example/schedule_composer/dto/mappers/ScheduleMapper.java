package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleRepository;
import com.example.schedule_composer.service.GroupCourseTeacherService;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper implements DTOMapper<ScheduleDTOGet, ScheduleDTOPost, ScheduleDTOPatch, Schedule, Long>{

    private final ScheduleRepository scheduleRepository;
    private final GroupCourseTeacherService groupCourseTeacherService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final GroupCourseTeacherMapper groupCourseTeacherMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;



    @Autowired
    public ScheduleMapper(ScheduleRepository scheduleRepository, GroupCourseTeacherService groupCourseTeacherService, RoomService roomService, TimeSlotService timeSlotService, CourseMapper courseMapper, GroupCourseTeacherMapper groupCourseTeacherMapper, RoomMapper roomMapper, TimeSlotMapper timeSlotMapper) {
        this.scheduleRepository = scheduleRepository;
        this.groupCourseTeacherService = groupCourseTeacherService;
        this.roomService = roomService;
        this.timeSlotService = timeSlotService;
        this.groupCourseTeacherMapper = groupCourseTeacherMapper;
        this.roomMapper = roomMapper;
        this.timeSlotMapper = timeSlotMapper;
    }
    @Override
    public ScheduleDTOGet fromEntityToGet(Schedule schedule) {
        ScheduleDTOGet scheduleGet = new ScheduleDTOGet(
                schedule.getId(),
                groupCourseTeacherMapper.fromEntityToGet(schedule.getGroupCourseTeacher()),
                roomMapper.fromEntityToGet(schedule.getRoom()),
                schedule.getDay(),
                timeSlotMapper.fromEntityToGet(schedule.getStartTimeSlot()),
                timeSlotMapper.fromEntityToGet(schedule.getEndTimeSlot()),
                schedule.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public Schedule fromPostToEntity(ScheduleDTOPost scheduleDTOPost) {
        GroupCourseTeacher groupCourseTeacher = groupCourseTeacherService.getEntityById(scheduleDTOPost.getGroupCourseTeacherId());
        Room room = roomService.getEntityById(scheduleDTOPost.getRoomId());
        TimeSlot startTimeSlot = timeSlotService.getEntityById(scheduleDTOPost.getStartTimeSlotId());
        TimeSlot endTimeSlot = timeSlotService.getEntityById(scheduleDTOPost.getEndTimeSlotId());


        Schedule schedule = Schedule.builder()
                .groupCourseTeacher(groupCourseTeacher)
                .room(room)
                .day(scheduleDTOPost.getDay())
                .startTimeSlot(startTimeSlot)
                .endTimeSlot(endTimeSlot)
                .teachingMode(scheduleDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public Schedule fromPatchToEntity(ScheduleDTOPatch scheduleDTOPatch, Long scheduleId) {

        Schedule existingSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule item not found with id: " + scheduleId));


        if(scheduleDTOPatch.getGroupCourseTeacherId() != null){
            GroupCourseTeacher groupCourseTeacher = groupCourseTeacherService.getEntityById(scheduleDTOPatch.getGroupCourseTeacherId());
            existingSchedule.setGroupCourseTeacher(groupCourseTeacher);
        }

        if(scheduleDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleDTOPatch.getRoomId());
            existingSchedule.setRoom(room);
        }

        if(scheduleDTOPatch.getDay() != null){
            existingSchedule.setDay(scheduleDTOPatch.getDay());
        }

        if(scheduleDTOPatch.getStartTimeSlotId() != null){
            TimeSlot startTimeSlot = timeSlotService.getEntityById(scheduleDTOPatch.getStartTimeSlotId());
            existingSchedule.setStartTimeSlot(startTimeSlot);
        }

        if(scheduleDTOPatch.getEndTimeSlotId() != null){
            TimeSlot endTimeSlot = timeSlotService.getEntityById(scheduleDTOPatch.getEndTimeSlotId());
            existingSchedule.setEndTimeSlot(endTimeSlot);
        }

        if(scheduleDTOPatch.getTeachingMode() != null){
            existingSchedule.setTeachingMode(scheduleDTOPatch.getTeachingMode());
        }

        return existingSchedule;

    }
}
