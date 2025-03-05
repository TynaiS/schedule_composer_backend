package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.dto.get.GroupDTOGet;
import com.example.schedule_composer.dto.get.TimeSlotDTOGet;
import com.example.schedule_composer.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ScheduleLunchDTOPost {

    private Long groupId;
    private DayOfWeek day;
    private Long startTimeSlotId;
    private Long endTimeSlotId;
}
