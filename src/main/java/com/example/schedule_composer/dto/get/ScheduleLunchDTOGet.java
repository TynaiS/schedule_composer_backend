package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.Group;
import com.example.schedule_composer.entity.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleLunchDTOGet {

    private Long id;
    private GroupDTOGet group;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
}
