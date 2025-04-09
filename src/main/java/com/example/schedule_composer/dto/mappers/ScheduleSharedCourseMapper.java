package com.example.schedule_composer.dto.mappers;

import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.dto.patch.ScheduleSharedCourseDTOPatch;
import com.example.schedule_composer.dto.post.ScheduleSharedCourseDTOPost;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleSharedCourseRepository;
import com.example.schedule_composer.service.CourseTeacherSharedService;
import com.example.schedule_composer.service.RoomService;
import com.example.schedule_composer.service.TimeSlotService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleSharedCourseMapper implements DTOMapper<ScheduleSharedCourseDTOGet, ScheduleSharedCourseDTOPost, ScheduleSharedCourseDTOPatch, ScheduleSharedCourse, Long>{

    private final ScheduleSharedCourseRepository scheduleSharedCourseRepository;
    private final CourseTeacherSharedService courseTeacherSharedService;
    private final RoomService roomService;
    private final TimeSlotService timeSlotService;
    private final CourseTeacherSharedMapper courseTeacherSharedMapper;
    private final RoomMapper roomMapper;
    private final TimeSlotMapper timeSlotMapper;



    @Autowired
    public ScheduleSharedCourseMapper(ScheduleSharedCourseRepository scheduleSharedCourseRepository, CourseTeacherSharedService courseTeacherSharedService, RoomService roomService, TimeSlotService timeSlotService, CourseMapper courseMapper, CourseTeacherSharedMapper courseTeacherSharedMapper, RoomMapper roomMapper, TimeSlotMapper timeSlotMapper) {
        this.scheduleSharedCourseRepository = scheduleSharedCourseRepository;
        this.courseTeacherSharedService = courseTeacherSharedService;
        this.roomService = roomService;
        this.timeSlotService = timeSlotService;
        this.courseTeacherSharedMapper = courseTeacherSharedMapper;
        this.roomMapper = roomMapper;
        this.timeSlotMapper = timeSlotMapper;
    }
    @Override
    public ScheduleSharedCourseDTOGet fromEntityToGet(ScheduleSharedCourse schedule) {
        ScheduleSharedCourseDTOGet scheduleGet = new ScheduleSharedCourseDTOGet(
                schedule.getId(),
                courseTeacherSharedMapper.fromEntityToGet(schedule.getCourseTeacherShared()),
                roomMapper.fromEntityToGet(schedule.getRoom()),
                schedule.getDay(),
                timeSlotMapper.fromEntityListToGetList(schedule.getTimeSlots()),
                schedule.getTeachingMode());
        return scheduleGet;
    }

    @Override
    public List<ScheduleSharedCourseDTOGet> fromEntityListToGetList(List<ScheduleSharedCourse> scheduleSharedCourses) {
        return scheduleSharedCourses.stream()
                .map(this::fromEntityToGet)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleSharedCourse fromPostToEntity(ScheduleSharedCourseDTOPost scheduleSharedCourseDTOPost) {
        CourseTeacherShared courseTeacherShared = courseTeacherSharedService.getEntityById(scheduleSharedCourseDTOPost.getCourseTeacherSharedId());
        Room room = roomService.getEntityById(scheduleSharedCourseDTOPost.getRoomId());
        List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedCourseDTOPost.getTimeSlotIds());


        ScheduleSharedCourse schedule = ScheduleSharedCourse.builder()
                .courseTeacherShared(courseTeacherShared)
                .room(room)
                .day(scheduleSharedCourseDTOPost.getDay())
                .timeSlots(timeSlots)
                .teachingMode(scheduleSharedCourseDTOPost.getTeachingMode())
                .build();
        return schedule;
    }

    @Override
    public ScheduleSharedCourse fromPatchToEntity(ScheduleSharedCourseDTOPatch scheduleSharedCourseDTOPatch, Long scheduleId) {

        ScheduleSharedCourse existingScheduleSharedCourse = scheduleSharedCourseRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule-Shared-Course item not found with id: " + scheduleId));

        if(scheduleSharedCourseDTOPatch.getCourseTeacherSharedId() != null){
            CourseTeacherShared courseTeacherShared = courseTeacherSharedService.getEntityById(scheduleSharedCourseDTOPatch.getCourseTeacherSharedId());
            existingScheduleSharedCourse.setCourseTeacherShared(courseTeacherShared);
        }

        if(scheduleSharedCourseDTOPatch.getRoomId() != null){
            Room room = roomService.getEntityById(scheduleSharedCourseDTOPatch.getRoomId());
            existingScheduleSharedCourse.setRoom(room);
        }

        if(scheduleSharedCourseDTOPatch.getDay() != null){
            existingScheduleSharedCourse.setDay(scheduleSharedCourseDTOPatch.getDay());
        }

        if(scheduleSharedCourseDTOPatch.getTimeSlotIds() != null && scheduleSharedCourseDTOPatch.getTimeSlotIds().size() > 0){
            List<TimeSlot> timeSlots = timeSlotService.checkIfAllExistAndGetEntities(scheduleSharedCourseDTOPatch.getTimeSlotIds());
            existingScheduleSharedCourse.setTimeSlots(timeSlots);
        }


        if(scheduleSharedCourseDTOPatch.getTeachingMode() != null){
            existingScheduleSharedCourse.setTeachingMode(scheduleSharedCourseDTOPatch.getTeachingMode());
        }

        return existingScheduleSharedCourse;

    }
}
