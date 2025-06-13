package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.types.TeachingMode;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Builder
public class ScheduleSharedItemDTOPatch {
    private Long setupSharedItemId;
    private Long roomId;
    private DayOfWeek day;
    private List<Long> timeSlotIds;
    private TeachingMode teachingMode;
}
