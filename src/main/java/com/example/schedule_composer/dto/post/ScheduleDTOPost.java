package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.GroupCourseTeacherDTOGet;
import com.example.schedule_composer.dto.get.RoomDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.entity.TimeSlot;
import com.example.schedule_composer.utils.TeachingMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleDTOPost {

    @NotNull(message = "Group-Course-Teacher ID cannot be null")
    private Long groupCourseTeacherId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "Day of the week cannot be null")
    private DayOfWeek day;

    @NotNull(message = "Time Slots cannot be null")
    @Size(min = 1, message = "Time Slots cannot be empty")
    private List<Long> timeSlotIds;

    @NotNull(message = "Teaching Mode cannot be null")
    private TeachingMode teachingMode;
}
