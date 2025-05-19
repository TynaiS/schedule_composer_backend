package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.utils.types.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleSharedItemDTOGet {

    private Long id;
    private Long scheduleVersionId;
    private SetupSharedItemDTOGet setupSharedItem;
    private RoomDTOGet room;
    private DayOfWeek day;
    private List<TimeSlotDTOGet> timeSlots;
    private TeachingMode teachingMode;
}
