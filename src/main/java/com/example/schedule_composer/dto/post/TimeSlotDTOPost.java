package com.example.schedule_composer.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TimeSlotDTOPost {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
