package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleVersionDTOPost {

    @NotBlank(message = "Schedule version name cannot be blank")
    private String name;
}
