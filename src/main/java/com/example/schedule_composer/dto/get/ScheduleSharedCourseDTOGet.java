package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.CourseTeacherShared;
import com.example.schedule_composer.entity.Room;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public class ScheduleSharedCourseDTOGet {

    private Long id;
    private CourseTeacherSharedDTOGet courseTeacherShared;
    private RoomDTOGet room;
    private DayOfWeek day;
    private TimeSlotDTOGet startTimeslot;
    private TimeSlotDTOGet endTimeslot;
    private TeachingMode teachingMode;
}
