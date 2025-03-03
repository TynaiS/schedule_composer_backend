package com.example.schedule_composer.dto;

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

    private Long id;
    private Long courseGroupTeacherId;
    private Long roomId;
    private DayOfWeek day;
    private TimeSlot startTimeSlot;
    private LocalTime endTimeSlot;
    private TeachingMode teachingMode;
}
