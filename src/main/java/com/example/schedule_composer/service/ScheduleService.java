package com.example.schedule_composer.service;

import com.example.schedule_composer.dto.get.ScheduleDTOGet;
import com.example.schedule_composer.dto.get.ScheduleLunchDTOGet;
import com.example.schedule_composer.dto.get.ScheduleSharedCourseDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.entity.*;
import com.example.schedule_composer.repository.ScheduleLunchRepository;
import com.example.schedule_composer.repository.ScheduleRepository;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleLunchRepository scheduleLunchRepository;


    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleLunchRepository scheduleLunchRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleLunchRepository = scheduleLunchRepository;

    }

    public List<ScheduleDTOGet> getSchedules() {
//        return scheduleRepository.findAll();
        return null;
//        to be implemented;
    }

    public ScheduleDTOGet getScheduleById(Long id) {
//        return scheduleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule item not found with id " + id));
        return null;
//        to be implemented;
    }






    public List<ScheduleSharedCourseDTOGet> getScheduleSharedCourses() {
        return null;
//      to be implemented
    }

    public ScheduleSharedCourseDTOGet getScheduleSharedCourseById(Long id) {
        return null;
//      to be implemented
    }







    public List<ScheduleLunchDTOGet> getScheduleLunches() {

//        return scheduleLunchRepository.findAll();
        return null;
//        to be implemented;
    }


    public ScheduleLunchDTOGet getScheduleLunchById(Long id) {
//        return scheduleLunchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Schedule lunch not found with id " + id));
        return null;
//        to be implemented;
    }


    @Transactional
    public Schedule createScheduleItem(GroupCourseTeacher groupCourseTeacher, Room room, DayOfWeek day, TimeSlot startTimeSlot, TimeSlot endTimeSlot, TeachingMode teachingMode) {
        Schedule newScheduleItem = Schedule.builder()
                .groupCourseTeacher(groupCourseTeacher)
                .room(room)
                .day(day)
                .startTimeslot(startTimeSlot)
                .endTimeslot(endTimeSlot)
                .teachingMode(teachingMode)
                .build();
        Schedule savedScheduleItem = scheduleRepository.save(newScheduleItem);
        System.out.println("Schedule item added with courseGroupId " + groupCourseTeacher.getId());
        return savedScheduleItem;
    }


    public List<TimeSlotDTOGet> getTimeslots() {
        return null;
//        to be implemented;
    }

    public TimeSlotDTOGet getTimeslotById(Long id) {
        return null;
//        to be implemented;
    }
}
