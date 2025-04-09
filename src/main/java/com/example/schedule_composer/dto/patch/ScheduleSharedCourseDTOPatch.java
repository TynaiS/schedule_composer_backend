package com.example.schedule_composer.dto.patch;

import com.example.schedule_composer.utils.TeachingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleSharedCourseDTOPatch {
    private Long courseTeacherSharedId;
    private Long roomId;
    private DayOfWeek day;
    private List<Long> timeSlotIds;
    private TeachingMode teachingMode;
}
