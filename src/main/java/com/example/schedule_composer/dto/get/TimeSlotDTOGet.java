package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
@Getter
@AllArgsConstructor
public class TimeSlotDTOGet {
    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
