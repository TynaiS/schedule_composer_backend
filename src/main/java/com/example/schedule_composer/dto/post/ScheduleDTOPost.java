package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ScheduleDTOPost {

    @NotNull(message = "Group-Course-Teacher ID cannot be null")
    private Long groupCourseTeacherId;

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
