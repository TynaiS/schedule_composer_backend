package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimeSlotDTOPost {

    @NotNull(message = "IsLunchAllowed field cannot be null")
    private Boolean isLunchAllowed;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;
}
