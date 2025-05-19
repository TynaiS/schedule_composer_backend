package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleLunchItemDTOGet {

    private Long id;
    private Long scheduleVersionId;
    private GroupDTOGet group;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
}
