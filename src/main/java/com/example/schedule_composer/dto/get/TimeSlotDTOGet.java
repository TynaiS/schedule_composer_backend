package com.example.schedule_composer.dto.get;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
@Getter
@AllArgsConstructor
public class TimeSlotDTOGet {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
}
