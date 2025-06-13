package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseDTOPost {

    @NotBlank(message = "Course name cannot be blank")
    private String name;

    @NotNull(message = "Credits must not be null")
    private Integer credits;
}
