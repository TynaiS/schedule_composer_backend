package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherDTOPost {

    @NotBlank(message = "Teacher Name cannot be blank")
    private String name;

    @NotNull(message = "Daily hours cannot be null")
    private Integer dailyHours;

    @NotNull(message = "Weekly hours cannot be null")
    private Integer weeklyHours;
}
