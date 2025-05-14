package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.types.TeachingMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleItemDTOPost {

    @NotNull(message = "Setup item ID cannot be null")
    private Long setupId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "Day of the week cannot be null")
    private DayOfWeek day;

    @NotNull(message = "Time Slots cannot be null")
    @Size(min = 1, message = "Time Slots cannot be empty")
    private List<Long> timeSlotIds;

    @NotNull(message = "Teaching Mode cannot be null")
    private TeachingMode teachingMode;
}
