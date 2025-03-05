package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ScheduleDTOPost {

    private Long groupCourseTeacherId;
    private Long roomId;
    private DayOfWeek day;
    private Long startTimeSlotId;
    private Long endTimeSlotId;
    private TeachingMode teachingMode;
}
