package com.example.schedule_composer.dto.patch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTOPatch {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
