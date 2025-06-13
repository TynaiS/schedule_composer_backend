package com.example.schedule_composer.dto.post;

import com.example.schedule_composer.utils.types.TeachingMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleSharedItemDTOPost {

    @NotNull(message = "SetupItem-Shared ID cannot be null")
    private Long setupSharedItemId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "DayOfWeek cannot be null")
    private DayOfWeek day;

    @NotNull(message = "TimeSlots cannot be null")
    @Size(min = 1, message = "TimeSlots cannot be empty")
    private List<Long> timeSlotIds;

    @NotNull(message = "TeachingMode cannot be null")
    private TeachingMode teachingMode;
}
