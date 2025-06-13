package com.example.schedule_composer.dto.patch;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleLunchItemDTOPatch {
    private Long groupId;
    private DayOfWeek day;
    private List<Long> timeSlotIds;
}
