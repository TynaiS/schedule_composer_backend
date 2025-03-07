package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.CourseTeacherSharedDTOGet;
import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
public class ScheduleSharedCourseDTOPost {
    @NotNull(message = "Course-Teacher-Shared ID cannot be null")
    private Long courseTeacherSharedId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "Day of the week cannot be null")
    private DayOfWeek day;

    @NotNull(message = "Start Time Slot ID cannot be null")
    private Long startTimeSlotId;

    @NotNull(message = "End Time Slot ID cannot be null")
    private Long endTimeSlotId;

    @NotNull(message = "Teaching Mode cannot be null")
    private TeachingMode teachingMode;
}
