package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.GroupCourseTeacher;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleDTOGet {

    private Long id;
    private GroupCourseTeacherDTOGet groupCourseTeacher;
    private RoomDTOGet room;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
    private TeachingMode teachingMode;
}
