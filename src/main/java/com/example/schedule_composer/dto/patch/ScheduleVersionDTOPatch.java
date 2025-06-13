package com.example.schedule_composer.dto.patch;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleVersionDTOPatch {

    @NotBlank(message = "Schedule version name cannot be blank")
    private String name;
}
