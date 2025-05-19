package com.example.schedule_composer.dto.get;

import com.example.schedule_composer.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleVersionDTOGet {
    private Long id;
    private Long scheduleId;
    private String name;
}
