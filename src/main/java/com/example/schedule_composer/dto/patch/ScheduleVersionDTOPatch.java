package com.example.schedule_composer.dto.patch;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleVersionDTOPatch {

    @NotBlank(message = "Schedule version name cannot be blank")
    private String name;
}
