package com.example.schedule_composer.dto.patch;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimeSlotDTOPatch {
    private Boolean isLunchAllowed;
    private LocalTime startTime;
    private LocalTime endTime;
}
