package com.example.schedule_composer.dto.post;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TimeSlotDTOPost {

    @NotNull(message = "IsLunchAllowed field cannot be null")
    private Boolean isLunchAllowed;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;
}
