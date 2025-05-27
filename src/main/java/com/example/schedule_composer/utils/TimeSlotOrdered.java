package com.example.schedule_composer.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotOrdered {

    private Long id;
    private Integer seqNumber;
    private Boolean isLunchAllowed;
    private LocalTime startTime;
    private LocalTime endTime;
}
