package com.example.schedule_composer.dto.patch;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTOPatch {
    private Boolean isLunchAllowed;
    private LocalTime startTime;
    private LocalTime endTime;
}
