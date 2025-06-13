package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SetupSharedSetDTOPost {

    @NotBlank(message = "SetupSharedSet name cannot be blank")
    private String name;

    @NotNull(message = "Hours a week cannot be null")
    private Integer hoursAWeek;
}
