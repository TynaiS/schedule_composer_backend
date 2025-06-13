package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleVersionDTOPost {

    @NotBlank(message = "Schedule version name cannot be blank")
    private String name;
}
