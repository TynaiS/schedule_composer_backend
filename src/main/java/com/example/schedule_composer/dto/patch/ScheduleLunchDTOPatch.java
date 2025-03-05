package com.example.schedule_composer.dto.patch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleLunchDTOPatch {
    private Long groupId;
    private DayOfWeek day;
    private Long startTimeSlotId;
    private Long endTimeSlotId;
}
