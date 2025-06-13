package com.example.schedule_composer.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentDTOPost {

    @NotBlank(message = "Department name cannot be blank")
    private String name;

}
