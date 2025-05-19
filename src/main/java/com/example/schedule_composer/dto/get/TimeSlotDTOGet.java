package com.example.schedule_composer.dto.get;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
@Getter
@Builder
public class TimeSlotDTOGet {
    private Long id;
    private Long scheduleId;
    private LocalTime startTime;
    private LocalTime endTime;
}
