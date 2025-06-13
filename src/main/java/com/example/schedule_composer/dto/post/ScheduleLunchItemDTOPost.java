package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleLunchItemDTOPost {

    @NotNull(message = "Group ID cannot be null")
    private Long groupId;

    @NotNull(message = "DayOfWeek cannot be null")
    private DayOfWeek day;

    @NotNull(message = "TimeSlots cannot be null")
    @Size(min = 1, message = "TimeSlots cannot be empty")
    private List<Long> timeSlotIds;
}
