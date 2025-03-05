package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.utils.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public class ScheduleSharedCourseDTOPost {
    private Long courseTeacherSharedId;
    private Long roomId;
    private DayOfWeek day;
    private Long startTimeSlotId;
    private Long endTimeSlotId;
    private TeachingMode teachingMode;
}
