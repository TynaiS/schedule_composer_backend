package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleDTOGet {

    private Long id;
    private SetupDTOGet setup;
    private RoomDTOGet room;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
    private TeachingMode teachingMode;
}
