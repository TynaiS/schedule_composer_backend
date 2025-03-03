package com.example.schedule_composer.dto;

import com.example.schedule_composer.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ScheduleLunchDTOPost {

    private Long id;
    private Long groupId;
    private DayOfWeek day;
    private TimeSlot startTimeSlot;
    private LocalTime endTimeSlot;
}
