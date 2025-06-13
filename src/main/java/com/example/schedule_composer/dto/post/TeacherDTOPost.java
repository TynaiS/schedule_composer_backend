package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDTOPost {

    @NotBlank(message = "Teacher Name cannot be blank")
    private String name;

    @NotNull(message = "Daily hours cannot be null")
    private Integer dailyHours;

    @NotNull(message = "Weekly hours cannot be null")
    private Integer weeklyHours;
}
