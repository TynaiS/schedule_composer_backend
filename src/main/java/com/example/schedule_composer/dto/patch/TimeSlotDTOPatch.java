package com.example.schedule_composer.dto.patch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTOPatch {
    private LocalTime startTime;
    private LocalTime endTime;
}
