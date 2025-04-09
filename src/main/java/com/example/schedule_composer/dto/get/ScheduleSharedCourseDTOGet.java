package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.CourseTeacherShared;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleSharedCourseDTOGet {

    private Long id;
    private CourseTeacherSharedDTOGet courseTeacherShared;
    private RoomDTOGet room;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
    private TeachingMode teachingMode;
}
