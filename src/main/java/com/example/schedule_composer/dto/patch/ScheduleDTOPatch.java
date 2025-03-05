package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTOPatch {
    private Long groupCourseTeacherId;
    private Long roomId;
    private DayOfWeek day;
    private Long startTimeSlotId;
    private Long endTimeSlotId;
    private TeachingMode teachingMode;
}
