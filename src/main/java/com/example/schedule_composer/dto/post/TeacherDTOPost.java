package com.example.schedule_composer.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherDTOPost {
    private String name;
    private Integer dailyHours;
    private Integer weeklyHours;
}
