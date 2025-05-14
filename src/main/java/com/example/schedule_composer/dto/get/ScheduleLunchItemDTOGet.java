package com.example.schedule_composer.dto.get;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleLunchItemDTOGet {

    private Long id;
    private GroupDTOGet group;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
}
