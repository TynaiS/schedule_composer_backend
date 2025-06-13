package com.example.schedule_composer.dto.patch;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDTOPatch {
    private String name;
    private Integer dailyHours;
    private Integer weeklyHours;
}
