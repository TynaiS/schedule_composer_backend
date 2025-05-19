package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.ScheduleVersion;
import com.example.schedule_composer.utils.types.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleItemDTOGet {

    private Long id;
    private Long scheduleVersionId;
    private SetupItemDTOGet setupItem;
    private RoomDTOGet room;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
    private TeachingMode teachingMode;
}
